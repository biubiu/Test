package com.shawn.concurrent.util;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

public class PhaserFileSearch {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(	3);
        FileSearch system = new FileSearch("/var/log", "log",  phaser);
        FileSearch app = new FileSearch("/usr/local/apache-tomcat-7.0.39", "log", phaser);
        FileSearch doc =new FileSearch("/home/shawncao", "log", phaser);
        Thread sysThread = new Thread(system,"System");
        sysThread.start();
        Thread appThread = new Thread(app,"app");
        appThread.start();
        Thread docThread = new Thread(doc,"doc");
        docThread.start();
        try {
            sysThread.join();
            appThread.join();
            docThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("Terminated: " + phaser.isTerminated());
    }
}

class FileSearch  implements Runnable{
    private String initPath;
    private String end;
    private List<String> results;
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.results = Lists.newArrayList();
        this.phaser = phaser;
    }

    private void direcotryProcess(File file) {
        File list[] = file.listFiles();
        if(list!=null){
            for (int i = 0; i < list.length; i++) {
                if(list[i].isDirectory()){
                    direcotryProcess(list[i]);
                }else {
                    fileProcess(file);
                }
            }
        }
    }

    private void fileProcess(File file){
        if(file.getName().endsWith(end)){
            results.add(file.getAbsolutePath());
        }
    }

    private void filterResults(){
        List<String> newResults = Lists.newArrayList();
        long date = new Date().getTime();
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            long fileDate = file.lastModified();
            if(date - fileDate < TimeUnit.MICROSECONDS.convert(1, TimeUnit.DAYS)){
                newResults.add(results.get(i));
            }
        }
        results = newResults ;
    }

    private boolean checkResults(){
        if(results.isEmpty()){
            System.out.printf("%s: Phase %d: 0 results.\n",Thread.currentThread().getName(),phaser.getPhase());
            System.out.printf("%s: Phase %d: 0 End.\n",Thread.currentThread().getName(),phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        }else {
            System.out.printf("%s: Phase %d: %d results.\n",Thread.currentThread().getName(),phaser.getPhase(),results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void showInfo(){
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            System.out.printf("%s: %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
        }
        phaser.arriveAndAwaitAdvance();
    }

    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting. \n",Thread.currentThread().getName());
        File file = new File(initPath);
        if(file.isDirectory()){
            direcotryProcess(file);
        }
        if(!checkResults()){
            return;
        }
        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed. \n",Thread.currentThread().getName());
    }
}
