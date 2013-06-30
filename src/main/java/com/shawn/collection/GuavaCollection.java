package com.shawn.collection;

import java.util.Set;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class GuavaCollection {
    public static void main(String[] args) {
        Set<Integer> setA = Sets.newHashSet(1,2,3,4,5,6);
        Set<Integer> setB = Sets.newHashSet(1,2,3,4,5,6,7,8);
        SetView<Integer> interSet  = Sets.intersection(setA, setB);
        interSet = Sets.difference(setB, setA);
        for (Integer i: interSet) {
            System.out.println(i);
        }
    }

}
