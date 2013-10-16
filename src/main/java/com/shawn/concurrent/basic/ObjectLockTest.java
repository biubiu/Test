package com.shawn.concurrent.basic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ObjectLockTest {

	static ListHelper<Integer> listHelper = new ListHelper<Integer>();
	public static void main(String[] args) throws InterruptedException {
		listHelper.list.add(1);
		listHelper.list.add(2);
		listHelper.list.add(3);
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i]= new Thread(new Runnable() {				
				public void run() {
					listHelper.putIfAbsent(10);
				}
			});
			threads[i].start();
			threads[i].join();
		}
		for (int i = 0; i < listHelper.list.size(); i++) {
			System.out.println(listHelper.list.get(i));
		}		
	}
}


class ListHelper<E>{
	public List<E> list = Collections.synchronizedList(new LinkedList<E>());	
	public  boolean putIfAbsent(E x){
		synchronized (list) {
			boolean absent = !list.contains(x);
			if(absent)
				list.add(x);
			return absent;
		}
	}
}
