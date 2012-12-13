package com.shawn.concurrent.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BackwardThread {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        Doc doc = new Doc();
        for( int i=0; i< threads.length; i++){
            threads[i] = new Thread(new DocPrint(i, doc));
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
            while(id+1 != numOfThread) {
                number.await();
            }
            System.out.printf("from thread %d \n", id+1);
            numOfThread--;
           number.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
