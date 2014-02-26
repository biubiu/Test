package com.shawn.template;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestString {


    @Test(expected=OutOfMemoryError.class)
    public void testStringPadding(){
        List<String> list = new ArrayList<>();
        int i=0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
