package com.shawn.concurrent.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutor {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int count = 0;
        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.scheduleAtFixedRate(new ScheduleJob(count,schedule), 0,500, TimeUnit.MILLISECONDS);
        /*{
            System.out.println("----------------------------");
            schedule.shutdown();
        }*/
    }
}

final class ScheduleJob implements Runnable{

    private int count;
    ScheduledExecutorService schedule;
    public ScheduleJob(int count,ScheduledExecutorService schedule) {
        this.count = count;
        this.schedule = schedule;
    }


    @Override
    public void run()  {
        System.out.println("count..." + count);
        if(count++ > 10){
            schedule.shutdown();
        }
    }

}