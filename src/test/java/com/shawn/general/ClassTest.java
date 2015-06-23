package com.shawn.general;

import org.junit.Test;
import java.util.Date;

/**
 * @author Shawn Cao
 */
public class ClassTest {

    @Test
    public void testSub(){
         Sub sub = new Sub();
        sub.overrideMe();
    }
}


class A{
    int a;
    int b;

    protected void setA(){

    }
}

interface C{
      void c();
}
class B extends A implements  C{

    @Override
    public void setA() {
        super.setA();
    }

    @Override
    public void c() {

    }
}


class Super{

    public Super(){
        overrideMe();
    }

    public void overrideMe(){

    }
}


final class Sub extends Super{

    private final Date date;

    Sub(){
        date = new Date();
    }

    @Override
    public void overrideMe() {
        System.out.println(date);
    }
}