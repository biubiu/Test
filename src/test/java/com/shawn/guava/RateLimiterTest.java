package com.shawn.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.internal.runners.statements.RunAfters;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Shangfei on 3/3/16.
 */
public class RateLimiterTest {

    static RateLimiter rateLimiter = RateLimiter.create(2.0);

    public static void main(String[] args) {

        List<Runnable> runs = Lists.newArrayListWithCapacity(10);
        for (int i = 0; i < 10 ; i++) {
            runs.add(new Runnable() {
                @Override
                public void run() {
                    System.out.println("print---- time  " + LocalTime.now().toString());
                }
            });
        }
        submitTasks(runs, Executors.newFixedThreadPool(10));
    }

    static void submitTasks(List<Runnable> tasks, ExecutorService executor) {
        for (Runnable e: tasks) {
            rateLimiter.acquire();
            executor.execute(e);
        }
        executor.shutdown();
    }
}
