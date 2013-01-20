package com.shawn.object;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.WeakHashMap;

public class EliminateObsoleteRefTest {

    /**
     * 2.another common source of memory leaks is
     * caches(easy to forget leave there after it comes irrelevant)
     * 	a.represent cache as <tt>WeakHashMap</tt> only if the desired lifetime of cahce
     * 	   entries is determined by external refs to the key not value
     * b.cleansed of entries by background thread(<tt>Timer</tt>,<tt>ScheduledThreadPoolExecutor</tt>)
     *   <tt>LinkedHashMap</tt> with its removeEldestEntry method
     * 3.third common source of mem leak listeners and other callbacks
     */

}

class Stack{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY=16;

    public Stack(){
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if(size == 0)
            throw new EmptyStackException();
        //memory leak, it will still keep the obsolete reference to this element
        //return elements[--size];
        //improve
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }
    private void ensureCapacity(){
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2*size + 1);
    }
}
