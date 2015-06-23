package com.shawn.general;

import com.google.common.base.Stopwatch;
import javafx.scene.paint.Stop;

import java.util.concurrent.TimeUnit;

/**
 * @author Shawn Cao
 */
public class LongObject {

    public static void main(String[] args){
        Stopwatch stopwatch = Stopwatch.createStarted();
        long sum = 0L;
        for(long i = 0; i<Integer.MAX_VALUE; i++){
            sum += i;
        }
        System.out.println(sum);
        stopwatch.stop();
        System.out.println("time:" +stopwatch.elapsed(TimeUnit.SECONDS));


    }
}
