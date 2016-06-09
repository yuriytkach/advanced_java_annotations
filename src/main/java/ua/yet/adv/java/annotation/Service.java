package ua.yet.adv.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Own annotation to mark service classes that should be loaded and analyzed for
 * <code>init</code> methods. The required attribute <code>name</code> and
 * not-required <code>lazyLoad</code> are used to configure some properties.
 * 
 * @author Yuriy Tkach
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    /**
     * Name of the service
     */
    String name();

    /**
     * Specifies, if the service should be loaded lazily. Default is
     * <code>false</code>
     */
    boolean lazyLoad() default false;

}
