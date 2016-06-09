package ua.yet.adv.java.annotation.services;

import ua.yet.adv.java.annotation.Service;

/**
 * Dummy class that is not really a service, since it is not marked with the
 * annotation @{@link Service}
 * 
 * @author Yuriy Tkach
 *
 */
public class DummyService {

    /**
     * Init method that will never be called by service loader
     */
    public void init() {
        System.out.println("I am dumb");
    }

}
