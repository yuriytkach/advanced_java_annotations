package ua.yet.adv.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Service;

/**
 * Loads services by their full class name. This class showcases Java Reflection
 * API to create instances of classes by class name and dynamically call their
 * methods marked with annotation @{@link Init} if <code>lazyLoad=false</code>.
 * In addition to that the class showcases the possibility to access private
 * fields and methods.
 *
 * @author Yuriy Tkach
 */
@Slf4j
public class ServiceLoader {

    /**
     * Map of services by their names
     */
    private static Map<String, Object> servicesMap = new HashMap<>();

    /**
     * Main method that calls service loading by providing full class name of
     * the service
     *
     * @param args No arguments are expected
     */
    public static void main(String[] args) {
        loadService("ua.yet.adv.java.annotation.services.SimpleService");
        loadService("ua.yet.adv.java.annotation.services.LazyService");
        loadService("ua.yet.adv.java.annotation.services.ThrowingService");
        loadService("ua.yet.adv.java.annotation.services.DummyService");
    }

    /**
     * Method gets the class object from the provided <code>className</code>.
     * Then inspects the class to find annotation @{@link Service}. If found,
     * then calls {@link #createAndInitService(Class)} method.
     *
     * @param className Full class name of the service
     */
    private static void loadService(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(Service.class)) {

                createAndInitService(clazz);

            } else {
                log.info("Failed to load service " + className
                        + ": No Service annotation present");
            }
        } catch (ClassNotFoundException e) {
            log.error("Failed to load service " + className + ": "
                    + e.getMessage());
        }
    }

    /**
     * Creates new instance of the service class and puts it into the map. If
     * <code>lazyLoad=false</code> then invokes init methods.
     *
     * @param clazz Service class object
     * @throws InstantiationException If instance of the service object can't be created
     * @throws IllegalAccessException If constructor of the service object can't be accessed
     */
    private static void createAndInitService(Class<?> clazz){
        try {
            Object serviceObj = clazz.getDeclaredConstructor().newInstance();

            Service annotation = clazz.getAnnotation(Service.class);

            servicesMap.put(annotation.name(), serviceObj);

            log.info("Added service instance for " + clazz.getName()
                    + ": " + annotation.name());

            if (!annotation.lazyLoad()) {
                setPrivateField(serviceObj);
                invokeInitMethods(clazz.getDeclaredMethods(), serviceObj);
            } else {
                log.info("  Service will lazy load");
            }
        } catch (InstantiationException | NoSuchMethodException
                | InvocationTargetException |IllegalAccessException e) {
            log.error("Failed to create service instance for "
                    + clazz.getName() + ": " + e.getMessage());
        }
    }

    /**
     * Invoking method if it has annotation @{@link Init} and does not accepts
     * arguments. If the method is private, then setting it accessible flag to
     * <code>true<code>.
     * If invocation throws exception, then catching it and re-throwing it if
     * <code>suppressException=false</code>. Otherwise, just output it.
     *
     * @param methods    Array of service's methods
     * @param serviceObj Service object
     */
    private static void invokeInitMethods(Method[] methods, Object serviceObj) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Init.class)) {

                if (method.getParameterTypes().length > 0) {
                    log.info("  Cannot call init method with arguments: "
                                    + method.getName());
                } else {
                    invokeInitMethod(serviceObj, method);
                }
            }
        }
    }

    private static void invokeInitMethod(Object serviceObj, Method method) {
        try {
            if (!Modifier.isPublic(method.getModifiers())) {
                method.setAccessible(true);
                log.info("  Made method accessible: "
                        + method.getName());
            }
            method.invoke(serviceObj);

            log.info("  Invoked init method for service: "
                    + method.getName());
        } catch (Exception e) {
            Init initAnnotation = method.getAnnotation(Init.class);

            if (initAnnotation.suppressException()) {
                log.info("  Error occurred during init: "
                        + e.getMessage());
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Trying to set private field value of the service object. The method will
     * fail if the field is not found. The method won't fail if the field is
     * found, however, the value won't change if the field is final primitive
     *
     * @param serviceObj Object of the service
     */
    private static void setPrivateField(Object serviceObj) {
        try {
            Field inits = serviceObj.getClass().getDeclaredField("inits");
            inits.setAccessible(true);
            inits.setInt(serviceObj, 45);

            Field fConst = serviceObj.getClass().getDeclaredField("CONST");
            fConst.setAccessible(true);
            fConst.setInt(serviceObj, 25);

        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            log.info("  Failed to set private field: " + e.getMessage());
        } catch (NoSuchFieldException e) {
            log.info("  Field 'inits' is not found in class");
        }
    }
}