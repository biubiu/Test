package com.shawn.enumNAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: Shawn cao
 * Date: 13-3-24
 * Time: PM6:53
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CheckValue {
    Class<?> value();
}
