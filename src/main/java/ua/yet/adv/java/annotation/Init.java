package ua.yet.adv.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Own annotation to mark <code>init</code> methods that will be called when
 * service is loaded
 * 
 * @author Yuriy Tkach
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Init {

    /**
     * Specifies whether exception after execution of <code>init</code> method
     * should be suppressed or not. Default is <code>false</code>, which means
     * that it is expected that exceptions in <code>init</code> method are
     * rethrown.
     */
    boolean suppressException() default false;

}
