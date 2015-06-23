package com.shawn.general;

import java.util.concurrent.TimeUnit;

/**
 * @author Shawn Cao
 */
public class ThreadTest {
    private static volatile boolean stopThread;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(!stopThread){
                    i++;
                }
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopThread = true;
    }
}


