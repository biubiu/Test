package com.shawn.leecode;

import java.util.Arrays;

/**
 * User: Shawn cao
 * Date: 14/12/26
 * Time: PM3:31
 */
/*
 Given an array and a value, remove all instances of that value in place and return the new length.
 The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */

public class Q27RemoveElement {
    public int removeElement(int[] A, int elem) {
        int tail = A.length-1;
        int i = 0;
        while ( i<=tail ){
            if (A[i]==elem){
                A[i] = A[tail--];
                continue;
            }
            i++;
        }

        return tail+1;


    }
}
