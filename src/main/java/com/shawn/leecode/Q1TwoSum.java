package com.shawn.leecode;



import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: Shawn cao
 * Date: 14/12/24
 * Time: PM12:08
 */
public class Q1TwoSum {

    public static int[] twoSum(int[] numbers, int target){


        Map<Integer, Integer> m = new LinkedHashMap<>((int)Math.ceil(numbers.length / 0.7));
        int[] result = new int[2];
        for(int i=0; i<numbers.length; i++){
            // not found the second one
            if (m.get(numbers[i]) == null) {
                // store the first one poisition into the second one's key
                m.put(target - numbers[i],i);

            }else {
                // found the second one
                result[0] = (m.get(numbers[i])+1);
                result[1] = i+1;
                break;
            }
        }
        return result;
    }

}
