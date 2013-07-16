package com.shawn.concurrent.basic;


public class SimpleTest {
	static A a = new A();
	
	private static boolean ready;
	private static int number;
	
	public static void main(String[] args) {
		noVisibilityTest();
		number = 42;
		ready = true;
	}
	
	private static void noVisibilityTest(){		
		new Thread(new Runnable() {			
			public void run() {
				System.out.println("run");
				while(!ready){
					Thread.yield();
				}
					System.out.println(number);
				
			}
		},"visible thread").start();
		//thread.start();
	}
	
	static void TestA() throws InterruptedException {

		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {		
				public void run() {
					a.addCount();					
				}
			},i+"");
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		System.out.println(a.count);
	}
}

class A{
	int count = 0;
	
	public void addCount(){
		//System.out.println("before:" +count);
		synchronized (this) {
			count++;	
		}
		
		//System.out.println(count + " :" + Thread.currentThread().getName());
	}
}