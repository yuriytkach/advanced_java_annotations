package ua.yet.adv.java.annotation.processor;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Note;
import ua.yet.adv.java.annotation.Service;
import ua.yet.adv.java.annotation.services.DummyService;
import ua.yet.adv.java.annotation.services.LazyService;
import ua.yet.adv.java.annotation.services.SimpleService;
import ua.yet.adv.java.annotation.services.ThrowingService;

/**
 * Processor to inspect provided classes and search for @{@link Service}
 * annotation in them. If found, then it searches for @{@link Note} annotations
 * to output notes and the it inspects methods of class and search for @
 * {@link Init} annotation on the method.
 *
 * The result of inspection is output to {@link System#out}.
 *
 * @author Yuriy Tkach
 *
 */
@Slf4j
public class AnnotationProcessor {

    /**
     * Main method that will call inspection on service classes
     *
     * @param args
     *            No arguments are expected
     */
    public static void main(String[] args) {

        inspectService(SimpleService.class);
        inspectService(LazyService.class);
        inspectService(ThrowingService.class);
        inspectService(DummyService.class);

    }

    /**
     * Inspector method. Checks if @{@link Service} annotation is present. If
     * found then outputs its params and searches for @{@link Note} annotations.
     * Then calls method to inspect and output more information about the class.
     *
     * @param service
     *            Service class object
     */
    private static void inspectService(Class<?> service) {
        if (service.isAnnotationPresent(Service.class)) {
            log.info("Class " + service.getSimpleName() + " has annotation @Service");
            Service annotation = service.getAnnotation(Service.class);
            log.info("  Name: " + annotation.name());
            if (annotation.lazyLoad()) {
                log.info("  Service should load lazy");
            }

            inspectNotes(service);

            inspectMethodInformation(service);

        } else {
            log.info("Class " + service.getSimpleName()
                    + " does not have annotation @Service");
        }
    }

    /**
     * Searches for @{@link Note} annotations and outputs their value if found
     *
     * @param service
     *            Service class object
     */
    private static void inspectNotes(Class<?> service) {
        Note[] notes = service.getAnnotationsByType(Note.class);
        if (notes != null && notes.length > 0) {
            log.info("  Founds notes:");
            for (Note note : notes) {
                log.info("    " + note.value());
            }
        } else {
            log.info("  No notes found");
        }
    }

    /**
     * Inspects declared method of the class and searches @{@link Init}
     * annotation on them. If found outputs parameters of annotation and checks
     * if method accepts arguments.
     *
     * @param service
     *            Service class object
     */
    private static void inspectMethodInformation(Class<?> service) {
        Method[] methods = service.getDeclaredMethods();
        if (methods.length > 0) {

            for (Method method : methods) {
                if (method.isAnnotationPresent(Init.class)) {
                    log.info("  Method " + method.getName()
                            + " has annotation @Init");

                    Init ann = method.getAnnotation(Init.class);
                    if (ann.suppressException()) {
                        log.info("    Exceptions of method will be suppressed");
                    }

                    if (method.getParameterTypes().length != 0) {
                        log.info("    Method expects arguments!");
                    }

                } else {
                    log.info("  Method " + method.getName()
                            + " does not have annotation @Init");
                }
            }

        } else {
            log.info("  Service has no methods.");
        }
    }
}
