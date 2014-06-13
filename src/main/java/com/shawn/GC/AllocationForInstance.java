package com.shawn.GC;

/**
 * User: Shawn cao
 * Date: 14-5-23
 * Time: PM5:37
 *
 *  test1 -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *  test2 -XX:PretenureSizeThreshold=3145728
 */
public class AllocationForInstance {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception{
        testAllocation();
        Thread.sleep(500);
        testAllocation();
    }

    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4 ;
        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
        //in test1: execute a minor GC because the space in Eden region (8M)is not enough for allocation4
        //in test2: assign in old generation region.
        allocation4 = new byte[4*_1MB];
    }
}
