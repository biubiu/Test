package com.shawn.leecode;

/**
 * User: Shawn cao
 * Date: 14/12/30
 * Time: PM4:32
 */

import java.util.Arrays;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.

 The digits are stored such that the most significant digit is at the head of the list.
 */
public class Q66PlusOne {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for(int i = digits.length -1 ; i >= 0 ; i--){
            int tmp = digits[i];
            tmp += carry;
            if(tmp >= 10){
                digits[i] = tmp -10 ;
            } else{
                digits[i] = tmp;
                carry = 0;
            }
        }

        if(carry == 1){
            int[] result = new int[digits.length+1];
            result[0] = carry;
            for(int i=1;i<result.length; i++){
                result[i] = digits[i-1];
            }
            return result;
        }
        return digits;
    }
}
