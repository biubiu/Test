package com.shawn.general;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
/**
 * @author Shawn Cao
 */
public class AnnotationTest {

    public static void main(String[] args){
            int tests = 0;
            int passed = 0;
            Class testClass = AnnotationTest.class;
            for (Method m : testClass.getMethods()) {
                if (m.isAnnotationPresent(Anno.class)) {
                    tests++;
                    try {
                        m.invoke(null);
                        System.out.printf("Test %s failed: no exception%n" ,m);
                    } catch (InvocationTargetException e) {
                        Throwable t = e.getCause();
                        Class<? extends Exception>[] excTypes = m.getAnnotation(Anno.class).value();
                        int oldPassed = passed;
                        for(Class<? extends Exception> ex:excTypes){
                            if(ex.isInstance(t)){
                                passed++;
                                break;
                            }
                        }
                        if(passed==oldPassed){
                            System.out.printf("Test %s failed: %s %n",m,t);
                        }

                    }catch (Exception e) {
                        System.out.println("Invalid @Test: " + m);
                    }
                }
            }

            System.out.printf("Passed:%d, failed:%d%n", passed, tests - passed);
    }

    @Anno({ArithmeticException.class,NullPointerException.class})
    public void m1(){
        int i=0;
        i = i/1;
    }

    @Anno({ArrayIndexOutOfBoundsException.class})
    public void m2(){
        int[] arr = new int[0];
        arr[1]=1;
    }
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Anno{
    Class<? extends Exception>[] value();
}