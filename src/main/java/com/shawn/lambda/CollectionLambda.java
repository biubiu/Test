package com.shawn.lambda;

import java.util.*;

/**
 * User: Shawn cao
 * Date: 14-3-25
 * Time: PM12:37
 */
public class CollectionLambda {

    public static void main(String[] args){
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
    }
}
