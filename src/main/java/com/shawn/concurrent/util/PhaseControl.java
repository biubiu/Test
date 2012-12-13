package com.shawn.concurrent.util;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.Finishings;

import org.apache.mahout.classifier.df.data.Data;

public class PhaseControl {
    public static void main(String[] args) {
        Phaser phaser = new CozPhaser();
        Student students[] = new Student[5];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(phaser);
            phaser.register();
        }
        Thread thread[] = new Thread[students.length];
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(students[i],"Student " + i);
            thread[i].start();
        }

        for (int i = 0; i < thread.length; i++) {
            try {
                thread[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class CozPhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
        case 0:
            return studentArrived();
        case 1:
            return finishFirstExercise();
        case 2:
            return finishSecondExercise();
        case 3:
            return finishExam();
        default:
            return false;
        }
    }


    public boolean studentArrived(){
        System.out.printf("Phaser: The exam are going to start.The students are ready. \n");
        System.out.printf("Phaser: We have %d students.\n",getRegisteredParties());
        return false;
    }

    public boolean finishFirstExercise(){
        System.out.printf("Phaser: All the students have finished the first exercise. \n");
        System.out.printf("Phaser: It's time for the second one.\n");
        return false;
    }
    public boolean finishSecondExercise(){
        System.out.printf("Phaser: All the students have finished the second exercise. \n");
        System.out.printf("Phaser: It's time for the third one.\n");
        return false;
    }

    public boolean finishExam(){
        System.out.printf("Phaser: All the students have finished the exam. \n");
        System.out.printf("Phaser: Thank u for ur time.\n");
        return false;
    }
}

class Student implements Runnable{
    private Phaser phaser;

    public Student(Phaser phaser){
        this.phaser = phaser;
    }

    public void run() {
        System.out.printf("%s: Has arrive to do the exam. %s \n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: is going to do the first exercise.%s \n",Thread.currentThread().getName(),new Date());
        doFirstExercise();
        System.out.printf("%s: Has done the first exercise.%s \n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: is going to do the second exercise.%s \n",Thread.currentThread().getName(),new Date());
        doSecondExercise();
        System.out.printf("%s: Has done the second exercise.%s \n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: is going to do the third exercise.%s \n",Thread.currentThread().getName(),new Date());
        doThridExercise();
        System.out.printf("%s: Has finished the exam.%s \n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();

    }

    public void doFirstExercise(){
        try {
            long duration = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doSecondExercise(){
        try {
            long duration = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doThridExercise(){
        try {
            long duration = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}