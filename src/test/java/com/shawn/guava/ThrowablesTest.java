package com.shawn.guava;

import com.google.common.base.Throwables;
import org.junit.Test;

import java.io.IOException;

/**
 * User: Shawn cao
 * Date: 14-6-10
 * Time: AM9:38
 */
public class ThrowablesTest {

    @Test
    public void testThrowable(){
        try{
            throw new Exception();
        }catch(Throwable t){
            String ss = Throwables.getStackTraceAsString(t);
            System.out.println(ss);
            Throwables.propagate(t);
        }
    }

    @Test
    public void call() throws IOException{
        try{
            throw new IOException();
        }catch(Throwable t){
            Throwables.propagateIfInstanceOf(t,IOException.class);
            throw Throwables.propagate(t);
        }
    }
}
