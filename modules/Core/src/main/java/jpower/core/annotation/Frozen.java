package jpower.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a Class or Package as being 'Frozen', as in it wont change until this annotation is removed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.PACKAGE})
public @interface Frozen {
   /**
    * The timestamp of being frozen
    *
    * @return timestamp
    */
   String value() default "";
}
