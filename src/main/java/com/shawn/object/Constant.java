package com.shawn.object;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: caocao024
 * Date: 13-1-17
 * Time: PM12:38
 * To change this template use File | Settings | File Templates.
 */
public class Constant {
    static final  int number1 = 5;

    static final  int number2 = 6;

    static int number3 = 5;

    static int number4= 6;

    public static void main(String[ ] args) {

        WeakReference<Integer> ints = new WeakReference<Integer>(new Integer(4));
        System.out.println(ints.get());

        int product1 = number1 * number2;         //line A

        int product2 = number3 * number4;         //line B

    }
}
