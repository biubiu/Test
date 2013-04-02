package com.shawn.concurrent;

import java.io.PrintStream;
import java.lang.Thread.State;

import com.google.common.eventbus.Subscribe;



public class BasicThreadTest {

    public static void main(String[] args) {
        //startThreadA();
        threadWithPriority();
    }

    public  static void startThreadA(){
        for (int i = 0; i < 10; i++) {
            Calculator calculator = new Calculator(i);
             Thread d= new Thread(calculator);
             d.start();
        }
    }

    public static  void threadWithPriority() {
        Thread threads[] = new Thread[10];
        Thread.State status[] = new Thread.State[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Calculator(i));
            if((i%2) == 0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("T " + i);
        }
        writeToFile(threads,status);
    }
    private static void writeToFile(Thread[] threads,Thread.State[] status){
        //FileWriter fileWriter;
        PrintStream pw;
        try {
            //fileWriter = new FileWriter("/home/shawncao/log.txt");
             //pw= new PrintWriter(fileWriter);
            pw = System.out;
             for (int i = 0; i < 10; i++) {
                 pw.println("Main: Status of Thread" + i + ":"+threads[i].getState());
             }
             boolean finish = false;
             while (!finish) {
                 for(int i=0; i<10;i++){
                     if(threads[i].getState()!=status[i]){
                         writeThreadInfo(pw,threads[i],status[i]);
                         status[i] = threads[i].getState();
                     }
                 }
                 finish = true;
                 for (int i = 0; i < 10; i++) {
                     finish = finish&&(threads[i].getState() == State.TERMINATED);
                }
             }
             pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }


    }

    private static void writeThreadInfo(PrintStream pw,Thread t , State state){
        pw.printf("Main: Id %d - %s\n",t.getId(),t.getName());
        pw.printf("Main:Priority: %d \n",t.getPriority());
        pw.printf("Main: Old State: %s\n",state);
        pw.printf("Main:New State: %s \n",t.getState());
        pw.printf("Main:**************************************************");
    }
}


class Calculator implements Runnable{
    private int number;
    public Calculator(int number) {
        this.number = number;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %d*%d = %d \n",Thread.currentThread().getName(),number,i,i*number);
        }
    }

}