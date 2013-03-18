package com.shawn.generalProgramming;

import java.util.Comparator;

/**
 *
 * @author shawncao
 *	when mix primitives and boxed primitives in a single operation, the boxed primitive is autounboxed
 *	autoboxing reduces the verbosity, but not the danger to using boxed primitives
 *	Alternatively ,it can throw a NullPointerException
 */
public class PrimitiveVSReferenceType {


    public static void comparatorTest(){
        Comparator<Integer> naturalOrder = new Comparator<Integer>() {
            public int compare(Integer first,Integer second){
                //first and second refer to same value but in different mem location where lead to== return false
            return first<second ? -1:(first == second ? 0 : 1);
            }
        };

        Comparator<Integer> correctNaturalOrder = new Comparator<Integer>() {
            public int compare(Integer first,Integer second){
                int f = first;//auto unboxing
                int s = second;
            return f<s? -1:(f== s ? 0 : 1);//no unboxing
            }
        };
    }
}
