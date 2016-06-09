package ua.yet.adv.java.annotation.services;

import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Service;

@Service(name = "Lazy service", lazyLoad = true)
public class LazyService {

	@Init
	public void init() throws Exception {
		System.out.println("I was lazy inited");
	}

	public void hahaMethod() {

	}

}
