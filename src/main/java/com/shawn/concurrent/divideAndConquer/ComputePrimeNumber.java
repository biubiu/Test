package com.shawn.concurrent.divideAndConquer;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;


public class ComputePrimeNumber {
	public static void main(String[] args) {
		//new SequentialPrimeFinder().timeAndCompute(10000000);
		new ConcurrentPrimeFinder(5, 5).timeAndCompute(10000000);
	}
}


class ConcurrentPrimeFinder extends AbstractPrimeFinder{

	private final int poolSize;
	private final int numberOfPart;
	public ConcurrentPrimeFinder(final int poolSize,final int numberOfPart) {
		this.poolSize = poolSize;
		this.numberOfPart = numberOfPart;
	}
	@Override
	public int countPrimes(int number) {
		int count = 0;
		final List<Callable<Integer>> partritons = Lists.newArrayList();
		final int chunksPerPartrition = number / numberOfPart;
		for (int i = 0; i < numberOfPart; i++) {
			final int lower = (i*chunksPerPartrition) + 1;
			final int upper = (i==numberOfPart-1)?number:lower + chunksPerPartrition-1;
			partritons.add(new Callable<Integer>() {
				public Integer call(){
					return countPrimeInRange(lower, upper);
				}
			});
		}
		final ExecutorService executor = Executors.newFixedThreadPool(poolSize);
		try {
			final List<Future<Integer>> resultsFromPartritions = executor.invokeAll(partritons,1000,TimeUnit.SECONDS);
			executor.shutdown();
			for (final Future<Integer> result: resultsFromPartritions) {
				count+=result.get();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}		
		return count;
	}
	
}

class SequentialPrimeFinder extends AbstractPrimeFinder{

	@Override
	public int countPrimes(int number) {
		return countPrimeInRange(1, number);
	}
	
}
abstract class AbstractPrimeFinder{
	
	public boolean isPrime(final int number){
		if(number <= 1) 
			return false;
		for(int i=2;i<Math.sqrt(number);i++){
			if(number %i ==0){
				return false;
			}
		}
		return true;
	}
	
	public int countPrimeInRange(final int lower, final int upper){
		int total = 0;
		for(int i=lower; i<=upper; i++){
			if(isPrime(i))
				total++;			
		}
		return total;
	}
	
	public void timeAndCompute(final int number){
		final long start  = System.nanoTime();
		final long numberOfPrimes = countPrimes(number);
		final long end = System.nanoTime();
		System.out.printf("Number of primes unber %d is %d \n",number,numberOfPrimes);
		System.out.printf("Time(seconds) taken is : "+ (end-start)/1.0e9);
	}
	
	public abstract int countPrimes(final int number);
}
