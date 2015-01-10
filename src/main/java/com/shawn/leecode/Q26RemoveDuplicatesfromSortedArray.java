package com.shawn.leecode;

/**
 * User: Shawn cao
 * Date: 14/12/26
 * Time: PM3:55
 */

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.ArrayUtils;
import org.apache.lucene.util.ArrayUtil;

import java.util.Arrays;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

   Do not allocate extra space for another array, you must do this in place with constant memory.
   For example,
   Given input array A = [1,1,2],

 Your function should return length = 2, and A is now [1,2].
 */
public class Q26RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] A) {
        int n = A.length;
        if (n<=1) return n;
        int pos=0;
        for(int i=0; i<n-1; i++){
            if (A[i]!=A[i+1]){
                A[++pos] = A[i+1];
                for(int j = 0; j<A.length; j++){
                    System.out.print(A[j]);
                }
                System.out.println("-----");
            }
        }
        for(int i = 0; i<A.length; i++){
            System.out.print(A[i]);
        }
        return pos+1;
    }
}
