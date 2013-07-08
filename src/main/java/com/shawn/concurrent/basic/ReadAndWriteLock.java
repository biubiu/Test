package com.shawn.concurrent.basic;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLock {
    public static void main(String[] args) {
        PriceInfo priceInfo = new PriceInfo();
        Reader[] readers = new Reader[5];
        Thread[] threadReaders = new Thread[5];

        for (int i = 0; i < threadReaders.length; i++) {
            readers[i] = new Reader(priceInfo);
            threadReaders[i] = new Thread(readers[i]);
        }

        Writer writer = new Writer(priceInfo);
        Thread threadWriter = new Thread(writer);

        for (int i = 0; i < 5; i++) {
            threadReaders[i].start();
        }
        threadWriter.start();
    }
}

class Writer implements Runnable {
    private PriceInfo priceInfo;

    public Writer(PriceInfo pricesInfo) {
        this.priceInfo = pricesInfo;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("Writer: Attempt to modify the prices.\n");
            priceInfo.setPrice(Math.random() * 10, Math.random() * 8);
            System.out.printf("Writer: Prices have been modified.\n");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Reader implements Runnable {
    private PriceInfo priceInfo;

    public Reader(PriceInfo pricesInfo) {
        this.priceInfo = pricesInfo;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), priceInfo.getPrice1());
            System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(), priceInfo.getPrice2());
        }
    }

}

class PriceInfo {
    private double price1;
    private double price2;
    private ReadWriteLock lock;

    public PriceInfo() {
        price1 = 1.0;
        price2 = 2.0;
        //lock = new ReentrantReadWriteLock();
        //with fairness
        lock = new ReentrantReadWriteLock(true);
    }

    public double getPrice1() {
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }

    public void setPrice(double price1, double price2) {
        lock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }
}
