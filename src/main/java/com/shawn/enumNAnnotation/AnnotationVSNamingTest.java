package com.shawn.enumNAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * User: shawncao Date: 13-1-30 Time: PM9:31
 */
public class AnnotationVSNamingTest {

    @TestSample
    public static void m1() {

    }

    @TestSample
    public static void m2() {
        throw new RuntimeException("Boom");
    }

    @TestSample
    public void m3() {

    }

    @TestSample
    public static void m4() {
        throw new RuntimeException("Crash");
    }

    @Test
    public void m5() {

    }

    @ExceptionTest(ArithmeticException.class)
    public static void e1() {
        int i = 0;
        i = 1/i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void e2() {
        int[] a = new int[10];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void e3() {
    }

    public static void testExceptionAnnotation() {
        int tests = 0;
        int passed = 0;
        Class testClass = AnnotationVSNamingTest.class;
        for (Method m : testClass.getMethods()) {
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n" ,m);
                } catch (InvocationTargetException e) {
                    Throwable t = e.getCause();
                    Class<? extends Exception> excType = m.getAnnotation(ExceptionTest.class).value();
                    if(excType.isInstance(t)){
                        passed++;
                    }else{
                        System.out.printf("Test %s failed: expected %s ,got %s%n", m, excType.getName(),t);
                    }
                }catch (Exception e) {
                    System.out.println("Invalid @Test: " + m);
                }
            }
        }
        System.out.printf("Passed:%d, failed:%d%n", passed, tests - passed);
    }

    public static void testSampleAnnotation() {
        int tests = 0;
        int passed = 0;
        Class testClass = AnnotationVSNamingTest.class;
        for (Method m : testClass.getMethods()) {
            if (m.isAnnotationPresent(TestSample.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException e) {
                    Throwable throwable = e.getCause();
                    System.out.println(m + " failed:" + throwable);
                } catch (Exception e) {
                    System.out.println("Invalid @TestSample: " + m);
                }
            }

        }
        System.out.printf("Passed:%d, failed:%d%n", passed, tests - passed);
    }

    public static void main(String[] args) {
        testExceptionAnnotation();
    }
}

/**
 * User: shawncao Date: 13-1-30 Time: PM10:11
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TestSample {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTest {
    Class<? extends Exception> value();
}
