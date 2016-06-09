package ua.yet.adv.java.annotation.services;

import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Service;

/**
 * Service that is loaded lazily. That means that the object won't be created
 * right away.
 * 
 * @author Yuriy Tkach
 *
 */
@Service(name = "Lazy service", lazyLoad = true)
public class LazyService {

    /**
     * Init method that will be executed only when service is loaded
     * 
     * @throws Exception
     *             If something happens :)
     */
    @Init
    public void init() throws Exception {
        System.out.println("I was lazy inited");
    }

    /**
     * Just another method
     */
    public void hahaMethod() {

    }

}
