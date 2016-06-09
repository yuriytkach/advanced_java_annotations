/**
 * 
 */
package ua.yet.adv.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Own annotation for holding notes. Can be specified multiple times as
 * repeatable annotation.
 * 
 * @author Yuriy Tkach
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NotesHolder.class)
public @interface Note {

    /**
     * Some value of the note
     */
    String value();
}
