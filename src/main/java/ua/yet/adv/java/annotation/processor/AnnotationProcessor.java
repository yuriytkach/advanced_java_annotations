package ua.yet.adv.java.annotation.processor;

import java.lang.reflect.Method;

import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Service;
import ua.yet.adv.java.annotation.services.DummyService;
import ua.yet.adv.java.annotation.services.LazyService;
import ua.yet.adv.java.annotation.services.SimpleService;
import ua.yet.adv.java.annotation.services.ThrowingService;

public class AnnotationProcessor {

	public static void main(String[] args) {

		inspectService(SimpleService.class);
		inspectService(LazyService.class);
		inspectService(ThrowingService.class);
		inspectService(DummyService.class);

	}

	private static void inspectService(Class<?> service) {
		if (service.isAnnotationPresent(Service.class)) {
			System.out.println("Class " + service.getSimpleName()
					+ " has annotation Service");

			Service annotation = service.getAnnotation(Service.class);
			System.out.println("Name of the service: " + annotation.name());
			if (annotation.lazyLoad()) {
				System.out.println("Service should load lazy");
			}

			outputMethodInformation(service);

		} else {
			System.out.println("Class " + service.getSimpleName()
					+ " does not have annotation Service");
		}
	}

	private static void outputMethodInformation(Class<?> service) {
		Method[] methods = service.getDeclaredMethods();
		if (methods.length > 0) {

			for (Method method : methods) {
				if (method.isAnnotationPresent(Init.class)) {
					System.out.println("Method " + method.getName()
							+ " has annotation Init");

					Init ann = method.getAnnotation(Init.class);
					if (ann.suppressException()) {
						System.out
								.println("Exceptions of method will be suppressed");
					}

					if (method.getParameterTypes().length != 0) {
						System.out.println("Method expects arguments!");
					}

				} else {
					System.out.println("Method " + method.getName()
							+ " does not have annotation Init");
				}
			}

		} else {
			System.out.println("Service has no methods.");
		}
	}
}
