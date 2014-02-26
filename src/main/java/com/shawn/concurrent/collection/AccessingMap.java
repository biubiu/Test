package com.shawn.concurrent.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccessingMap {
    public static void main(String[] args) {
        System.out.println("Using plain map:");
        useMap(new HashMap<String,Integer>());

        System.out.println("Using Synchronized map: ");
        useMap(Collections.synchronizedMap(new HashMap<String,Integer>()));

        System.out.println("Using Concurrent map: ");
        useMap(new ConcurrentHashMap<String, Integer>());
    }

    private static void useMap(final Map<String,Integer> scores){
        scores.put("Fred", 10);
        scores.put("Selly", 20);

        try{
            for (final String score: scores.keySet()) {
                System.out.println(score+ "score: " + scores.get(score));
                scores.put("Joe", 14);
            }
        }catch(Exception e){
            System.out.println("Failed: " + e);
        }
        System.out.println("Number of elements in maps " + scores.keySet().size());
    }
}
