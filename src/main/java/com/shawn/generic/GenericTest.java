package com.shawn.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;


/**
 *
 * @author shawncao
 *  parameterized type: List<String>
 *  Actual type parameter:String
 *  Generic type: List<E>
 *  Unbounded wildcard type: List<?>
 *  Raw type: List
 *  Bounded type parameter: <E extends Number>
 *  Recursive type bound: <T extends Comparable<T>>
 *  Bounded wildcard type : List<? extends Number>
 *  Generic method : static <E> List<E> asList(E[] a)
 */
public class GenericTest {

    public <T> T[] toArray(T[] a) {

        return a;
    }

    public void listOverArr() {
        // prefer list to array
        // fails at runtime
        Object[] objects = new Long[1];
        objects[0] = "won't fit in ";

        // fail at compile
        // List<Object> ol = new ArrayList<Long>();
    }

    public static void main(String[] args) {

    }

}

class Stack<T> {
    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push(T e) {
            ensureCapacity();
            elements[size++] = e;
    }
    public T pop() {
        if (size == 0)
            throw new EmptyStackException();
        T result = (T)elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    public void pushAll(Iterable<T> src){
        for(T t:src){
            push(t);
        }
    }
}


