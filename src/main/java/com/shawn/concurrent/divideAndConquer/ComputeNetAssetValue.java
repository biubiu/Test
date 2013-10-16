package com.shawn.concurrent.divideAndConquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class ComputeNetAssetValue{
	public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
		//new SequentialNetAssetValue().timeAndComputeValue();
		new ConcurrentNetAssetValue().timeAndComputeValue();
	}
}


class ConcurrentNetAssetValue extends AbstractNetAssetvValue{

	@Override
	public double computeNetAssetValue(final Map<String, Integer> stocks)
			throws ExecutionException, InterruptedException, IOException {
		final int noOfCores = Runtime.getRuntime().availableProcessors();
		//assuming the io cost the 90% of the total execution time 
		final double blockingCoeffiecent=0.9;
		final int poolSize = (int)(noOfCores / (1-blockingCoeffiecent));
		System.out.println("No of cores available is " + noOfCores);
		System.out.println("Pool size is : " + poolSize);
		final List<Callable<Double>> partritions = Lists.newArrayList();
		
		for (final String ticker:stocks.keySet()) {
			partritions.add(new Callable<Double>() {
				public Double call() throws Exception{
					return stocks.get(ticker) * YahooFinance.getPrice(ticker);
				}
			});
		}
		
		double netAssetValue = 0.0;
		final ExecutorService executor = Executors.newFixedThreadPool(poolSize);		
		final List<Future<Double>> valueOfStocks = executor.invokeAll(partritions,1000,TimeUnit.SECONDS);
		for (final Future<Double> e:valueOfStocks){
			netAssetValue +=e.get() ;
		}
		executor.shutdown();			
		return netAssetValue;
	}
	
}

 class SequentialNetAssetValue extends AbstractNetAssetvValue{

	@Override
	public double computeNetAssetValue(Map<String, Integer> stocks)
			throws ExecutionException, InterruptedException, IOException {
			double netAssetValue = 0.0;
			for(String ticker:stocks.keySet()){
				netAssetValue += stocks.get(ticker) * YahooFinance.getPrice(ticker);
			}
		return netAssetValue;
	}
}

 class YahooFinance {
	public static double getPrice(final String ticker) throws IOException{
		final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);		
		final BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		//final String discardHeader = br.readLine();
		final String data = br.readLine();
		final String[] dataItems = data.split(",");
		final double priceIsTheLastValue=Double.valueOf(dataItems[dataItems.length - 1]);
		return priceIsTheLastValue;
	}
}




abstract class AbstractNetAssetvValue{
	public static Map<String, Integer> readTickets() throws IOException{
		final BufferedReader br = new BufferedReader(new FileReader("src/main/resources/stocks.txt"));		
		final Map<String, Integer> stocks = Maps.newHashMap();
		String stockInfo = null;
		while((stockInfo = br.readLine())!=null){
			final String[] stockInfoData = stockInfo.split(",");
			final String stockTicker = stockInfoData[0];
			final Integer quantity = Integer.valueOf(stockInfoData[1]);
			stocks.put(stockTicker, quantity);
		}
		return stocks;
	}
	
	public void timeAndComputeValue() throws ExecutionException,InterruptedException,IOException{
		final long start = System.nanoTime();
		final Map<String, Integer> stocks = readTickets();
		final double nav = computeNetAssetValue(stocks);
		final long end = System.nanoTime();
		final String value = new DecimalFormat("$##,##0.00").format(nav);
		System.out.println("Your net asset value is " + value);
		System.out.println("Time (seconds) taken " + (end - start)/1.0e9);		
	}
	
	public abstract double computeNetAssetValue(final Map<String,Integer> stocks) throws ExecutionException,InterruptedException,IOException;		
}
