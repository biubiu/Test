package com.shawn.concurrent.basic;

import java.util.Date;
import java.util.LinkedList;

public class ProducerConsumerSyncVersion {

	public static void main(String[] args) {
		EventStorage storage = new EventStorage(10);
		ProducerV1 producer = new ProducerV1(storage);
		ConsumerV1 consumer = new ConsumerV1(storage);
		Thread thread1 = new Thread(producer);
		Thread thread2 = new Thread(consumer);
		thread2.start();
		thread1.start();
	}
}

class ConsumerV1 implements Runnable {
	private EventStorage storage;

	public ConsumerV1(EventStorage storage) {
		this.storage = storage;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}
}

class ProducerV1 implements Runnable {
	private EventStorage storage;

	public ProducerV1(EventStorage storage) {
		this.storage = storage;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.set();
		}
	}

}

class EventStorage {
	int maxSize;
	LinkedList<Date> storage;

	public EventStorage(int maxSize) {
		this.maxSize = maxSize;
		storage = new LinkedList<Date>();
	}

	public synchronized void set() {
		while (storage.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		storage.offer(new Date());
		System.out.printf("Set: %d \n", storage.size());
		notifyAll();
	}

	public synchronized void get() {
		while (storage.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Get: %d: %s", storage.size(),
				((LinkedList<?>) storage).poll());
		notifyAll();
	}
}
