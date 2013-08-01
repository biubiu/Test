package com.shawn.concurrent.basic;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class ProducerConsumerSemaphoreVersion {

	public static void main(String[] args) {
		EventStorageWithSemaphore storage = new EventStorageWithSemaphore(10);
		ProducerV3 producer = new ProducerV3(storage);		
		ConsumerV3 consumer = new ConsumerV3(storage);
		Thread thread1 = new Thread(producer);
		Thread thread2 = new Thread(consumer);
		thread1.start();
		thread2.start();
		
	}
}

class ConsumerV3 implements Runnable {
	private EventStorageWithSemaphore storage;

	public ConsumerV3(EventStorageWithSemaphore storage) {
		this.storage = storage;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}
}

class ProducerV3 implements Runnable {
	private EventStorageWithSemaphore storage;

	public ProducerV3(EventStorageWithSemaphore storage) {
		this.storage = storage;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.set();
		}
	}

}

class EventStorageWithSemaphore {
	int maxSize;
	LinkedList<Date> storage;
	private final Semaphore notFull;
	private final Semaphore notEmpty = new Semaphore(0);
	private final Semaphore access = new Semaphore(1);
	
	public EventStorageWithSemaphore(int maxSize) {
		this.maxSize = maxSize;
		storage = new LinkedList<Date>();
		notFull = new Semaphore(maxSize);
	}

	public void set() {		
		try {
			notFull.acquire();
			access.acquire();
			storage.offer(new Date());
			System.out.printf("Set: %d \n", storage.size());			
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} finally {
			access.release();
			notEmpty.release();			
		}
	}

	public void get() {
		
		try {
			notEmpty.acquire();
			access.acquire();
			System.out.printf("Get: %d: %s \n", storage.size(), storage.poll());						
		} catch (InterruptedException e) {			
			System.out.println(e.toString());
		} finally {
			access.release();
			notFull.release();
		}
	}
}
