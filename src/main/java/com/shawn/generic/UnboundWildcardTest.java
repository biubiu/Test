package com.shawn.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
/**
 * PECS: producer-extends,consumer-super
 *
 *
 */
public class UnboundWildcardTest {

    public static void main(String[] args) {
        WildcardStack<Number> stacks = new WildcardStack<Number>();
        Iterable<Integer> ints = Lists.newArrayList(1,2,3,4);
        stacks.pushAllByWildcard(ints);
        //not applicable for ints
        //stacks.pushAll(ints);
        Set<Integer> intSets = Sets.newHashSet(1,2,3,4);
        Set<Double> doubleSets = Sets.newHashSet(2.0,4.5,7.11);
        //Set<Number> mergeSets = union(intSets, doubleSets);
        Set<Number> mergeSets = WildcardStack.<Number>unionbyWildcard(intSets, doubleSets);
    }


}

class WildcardStack<T> extends Stack<T>{
    public void pushAllByWildcard(Iterable<? extends T> src) {
        for (T t:src) {
            push(t);
        }
    }

    public void popAllByWildcard(Collection<? super T> dst){
        while(!isEmpty()){
            dst.add(pop());
        }
    }

    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
     }

    public static <E> Set unionbyWildcard(Set<? extends E> s1, Set<? extends E> s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
     }
}
