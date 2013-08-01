package com.shawn.concurrent.basic;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerDemo {
	public static void main(String[] args) {
		Storage storage = new SyncEventStorage(10);
		// Storage storage = new StorageWithCondition(10);
		// Storage storage = new StorageWithSemaphore(10);
		Producer producer = new Producer(storage);
		Consumer consumer = new Consumer(storage);
		Thread thread1 = new Thread(producer);
		Thread thread2 = new Thread(consumer);
		thread1.start();
		thread2.start();
	}

}

class Consumer implements Runnable {
	private Storage storage;

	public Consumer(Storage storage) {
		this.storage = storage;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}

}

class Producer implements Runnable {
	private Storage storage;

	public Producer(Storage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.set();
		}
	}

}

class StorageWithSemaphore implements Storage {
	int maxSize;
	LinkedList<Date> storage;
	private final Semaphore notFull;
	private final Semaphore notEmpty = new Semaphore(0);
	private final Semaphore access = new Semaphore(1);

	public StorageWithSemaphore(int maxSize) {
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

class StorageWithCondition implements Storage {
	int maxSize;
	LinkedList<Date> storage;
	private final ReentrantLock reentrantLock = new ReentrantLock();
	private Condition notFull = reentrantLock.newCondition();
	private Condition notEmpty = reentrantLock.newCondition();

	public StorageWithCondition(int maxSize) {
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
		} catch (InterruptedException | IllegalMonitorStateException e) {
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
		} catch (InterruptedException | IllegalMonitorStateException e) {
			System.out.println(e.toString());
		} finally {
			reentrantLock.unlock();
		}
	}
}

class SyncEventStorage implements Storage {
	int maxSize;
	LinkedList<Date> storage;

	public SyncEventStorage(int maxSize) {
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
		System.out.printf("Get: %d: %s \n", storage.size(),
				((LinkedList<?>) storage).poll());
		notifyAll();
	}
	
	public synchronized void aa() {
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

interface Storage {

	public void set();

	public void get();

}