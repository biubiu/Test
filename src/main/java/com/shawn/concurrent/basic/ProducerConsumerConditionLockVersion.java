package com.shawn.concurrent.basic;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerConditionLockVersion {

	public static void main(String[] args) {
		EventStorageWithCondition storage = new EventStorageWithCondition(10);
		ProducerV2 producer = new ProducerV2(storage);
		
		ConsumerV2 consumer = new ConsumerV2(storage);
		Thread thread1 = new Thread(producer);
		Thread thread2 = new Thread(consumer);
		thread1.start();
		thread2.start();
		
	}
}

class ConsumerV2 implements Runnable {
	private EventStorageWithCondition storage;

	public ConsumerV2(EventStorageWithCondition storage) {
		this.storage = storage;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}
}

class ProducerV2 implements Runnable {
	private EventStorageWithCondition storage;

	public ProducerV2(EventStorageWithCondition storage) {
		this.storage = storage;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.set();
		}
	}

}

class EventStorageWithCondition {
	int maxSize;
	LinkedList<Date> storage;
	private final ReentrantLock reentrantLock = new ReentrantLock();
	private  Condition notFull = reentrantLock.newCondition();
	private  Condition notEmpty = reentrantLock.newCondition();

	public EventStorageWithCondition(int maxSize) {
		this.maxSize = maxSize;
		storage = new LinkedList<Date>();
	}

	public void set() {
		reentrantLock.lock();
		try {
			while (storage.size() == maxSize) {
				notFull.await();
			}
			storage.offer(new Date());
			System.out.printf("Set: %d \n", storage.size());
			notEmpty.signalAll();
		} catch (InterruptedException|IllegalMonitorStateException e) {
			System.out.println(e.toString());
		} finally {
			reentrantLock.unlock();
		}
	}

	public void get() {
		reentrantLock.lock();
		try {
			while (storage.size() == 0) {
				notEmpty.await();
			}
			System.out.printf("Get: %d: %s \n", storage.size(), storage.poll());
			notFull.signalAll();			
		} catch (InterruptedException| IllegalMonitorStateException e) {			
			System.out.println(e.toString());
		} finally {
			reentrantLock.unlock();
		}
	}
}
