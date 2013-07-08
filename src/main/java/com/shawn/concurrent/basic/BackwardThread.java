package com.shawn.concurrent.basic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BackwardThread {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        Doc doc = new Doc();
        for( int i=0; i< threads.length; i++){
            threads[i] = new Thread(new DocPrint(i, doc),i+"");
            threads[i].start();
        }
    }
}

class DocPrint implements Runnable{
    private Doc doc;
    private int id;
    public DocPrint(int id,Doc doc) {
        this.id = id;
        this.doc = doc;
    }
    public void run() {
      doc.printSelf(id);
    }

}
class Doc {
    private Condition number;
    private ReentrantLock lock;
    private int numOfThread = 10;

    public Doc() {
        lock = new ReentrantLock();
        number = lock.newCondition();
    }

    public void printSelf(int id) {

        lock.lock();
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            while(id+1 != numOfThread) {
                System.out.printf("%s is waiting...\n",Thread.currentThread().getName());
                number.await();
            }
            System.out.printf("thread %s is executing \n", Thread.currentThread().getName());
            numOfThread--;
           number.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
