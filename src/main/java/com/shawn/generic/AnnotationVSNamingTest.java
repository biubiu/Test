package com.shawn.generic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: caocao024
 * Date: 13-1-30
 * Time: PM9:31
 * To change this template use File | Settings | File Templates.
 */
public class AnnotationVSNamingTest {
}

/**
 * Created with IntelliJ IDEA.
 * User: caocao024
 * Date: 13-1-30
 * Time: PM10:11
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
 @interface Test {
}


