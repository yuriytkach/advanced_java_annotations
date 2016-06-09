package ua.yet.adv.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Service;

public class ServiceLoader {

	private static Map<String, Object> servicesMap = new HashMap<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loadService("ua.yet.adv.java.annotation.services.SimpleService");
		loadService("ua.yet.adv.java.annotation.services.LazyService");
		loadService("ua.yet.adv.java.annotation.services.ThrowingService");
		loadService("ua.yet.adv.java.annotation.services.DummyService");
	}

	private static void loadService(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			if (clazz.isAnnotationPresent(Service.class)) {

				try {
					createAndInitService(clazz);

				} catch (InstantiationException | IllegalAccessException e) {
					System.err.println("Failed to create service instance for "
							+ className + ": " + e.getMessage());
				}

			} else {
				System.err.println("Failed to load service " + className
						+ ": No Service annotation present");
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Failed to load service " + className + ": "
					+ e.getMessage());
		}
	}

	private static void createAndInitService(Class<?> clazz)
			throws InstantiationException, IllegalAccessException {
		Object serviceObj = clazz.newInstance();

		Service annotation = clazz.getAnnotation(Service.class);

		servicesMap.put(annotation.name(), serviceObj);

		System.out.println("Added service instance for " + clazz.getName()
				+ ": " + annotation.name());

		if (!annotation.lazyLoad()) {
			setPrivateField(serviceObj);
			invokeInitMethods(clazz.getDeclaredMethods(), serviceObj);
		} else {
			System.out.println("Service will lazy load");
		}
	}

	private static void invokeInitMethods(Method[] methods, Object serviceObj) {
		for (Method method : methods) {
			if (method.isAnnotationPresent(Init.class)) {

				if (method.getParameterTypes().length > 0) {
					System.err
							.println("Cannot call init method with arguments: "
									+ method.getName());
				} else {

					try {

						if (!Modifier.isPublic(method.getModifiers())) {
							method.setAccessible(true);
							System.out.println("Made method accessible: "
									+ method.getName());
						}
						method.invoke(serviceObj);

						System.out.println("Invoked init method for service: "
								+ method.getName());

					} catch (Throwable e) {
						Init initAnnotation = method.getAnnotation(Init.class);

						if (initAnnotation.suppressException()) {
							System.err.println("Error occured during init: "
									+ e.getMessage());
						} else {
							throw new RuntimeException(e);
						}
					}
				}
			}
		}
	}

	private static void setPrivateField(Object serviceObj) {
		Field inits;
		try {
			inits = serviceObj.getClass().getDeclaredField("inits");
			inits.setAccessible(true);
			inits.setInt(serviceObj, 45);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			System.err
					.println("Failed to set private field: " + e.getMessage());
		}
	}
}
