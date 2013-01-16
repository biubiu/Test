package com.shawn.test.ClazNInter;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: caocao024
 * Date: 13-1-9
 * Time: AM9:39
 * To change this template use File | Settings | File Templates.
 */
public class ImmutableTest {

    @Test
    public void testString(){
        String s = " Hello ";
        s += " world ";
        s.trim();
        System.out.println(s);
        Assert.assertNotSame(s,"Hello world");
    }

    @Test
    public void testStirngBuffer(){

    }
}

