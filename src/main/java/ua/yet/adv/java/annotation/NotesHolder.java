/**
 * 
 */
package ua.yet.adv.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Wrapper annotation for @{@link Note}s
 * 
 * @author Yuriy Tkach
 */
@Documented
@Inherited
@Retention (RetentionPolicy.RUNTIME)
public @interface NotesHolder {
    
    /**
     * Array of @{@link Note}s annotations. Default is empty array.
     */
    Note[] value() default {};
}
