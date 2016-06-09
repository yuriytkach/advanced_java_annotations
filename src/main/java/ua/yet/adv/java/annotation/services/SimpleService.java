package ua.yet.adv.java.annotation.services;

import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Service;

@Service(name = "Simple service")
public class SimpleService {

	private int inits = 0;

	@Init
	public void initService() {
		System.out.println("Initializing...");
		inits++;
	}

	@Init
	public void initServiceArgs(int arg) {
		System.out.println("Initializing with args ...");
	}

	@Init
	private void privateInit() {
		System.out.println("Initializing in private..." + inits);
	}

	public void someOtherMethod() {

	}

}
