package com.shawn.nio;

import org.junit.Test;
import static org.junit.Assert.*;

public class BufferTest{

    @Test
    public void testStringEqual(){
        String str1 = new StringBuilder("计算机").append("软件").toString();
        String str2 = new StringBuilder("ja").append("va").toString();
        assertTrue(str1.intern() == str1);
        assertTrue(str2.intern() == str2);
    }

}
