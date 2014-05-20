package com.shawn.general;

import org.github.jamm.MemoryMeter;
import org.junit.Test;

import java.time.LocalTime;

import static java.time.temporal.ChronoField.NANO_OF_DAY;


/**
 * User: Shawn cao
 * Date: 14-5-19
 * Time: AM11:25
 */
public class ObjectSizeTest {

    private static MemoryMeter meter = new MemoryMeter();

    @Test
    public void testPrimaryType(){
        StringBuilder sb = new StringBuilder();
        for(int i=0 ; i< 1000; i++){
            sb.append("hello meter and getting longlong");
        }
        String longstr = sb.toString();
        String shortStr = "a";
        Integer int1 = 100023;
        int int2 = 100023;
        long long1 = LocalTime.now().getLong(NANO_OF_DAY);
        boolean flag = false;
        System.out.println(meter.measureDeep(longstr) + " | " + meter.measure(longstr));
        System.out.println(meter.measureDeep(shortStr) + " | " + meter.measure(shortStr));
        System.out.println(meter.measureDeep(int1) + " | " + meter.measure(int1));
        System.out.println(meter.measureDeep(int2) + " | " + meter.measure(int2));
        System.out.println(meter.measureDeep(long1) + " | " + meter.measure(long1));
        System.out.println(meter.measureDeep(flag) + " | " + meter.measure(flag));
    }
}
