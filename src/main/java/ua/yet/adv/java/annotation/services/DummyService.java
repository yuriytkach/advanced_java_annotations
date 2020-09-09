package ua.yet.adv.java.annotation.services;

import lombok.extern.slf4j.Slf4j;
import ua.yet.adv.java.annotation.Service;

/**
 * Dummy class that is not really a service, since it is not marked with the
 * annotation @{@link Service}
 * 
 * @author Yuriy Tkach
 *
 */
@Slf4j
public class DummyService {

    /**
     * Init method that will never be called by service loader
     */
    public void init() {
        log.info("I am dumb");
    }

}
