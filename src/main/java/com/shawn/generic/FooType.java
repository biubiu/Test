package com.shawn.generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class FooType<T> {
    public <E> void test(final Collection<E> collection){
        for(final E e:collection){
            System.out.println("E: " + e);
        }
    }

    public void test(final Set<T> sets){
        for(final T t:sets){
            System.out.println("T from set: " + t);
        }
    }

    public void test(final List<Integer> intList){
        int result = 0 ;
        for(final Integer integer: intList){
            result+=integer.intValue();
        }
        System.out.println("result: " + result);
    }

    public static void main(String[] args) {
      /*  final FooType<String> fooType = new FooType<>();
        final List<String> list = Arrays.asList("some","test","value");
        fooType.test(list);*/

        final FooType<Integer> ints = new FooType<>();
        final List<Integer> intList = Arrays.asList(1,2,3,4,5);
        ints.test(intList);
    }
}
