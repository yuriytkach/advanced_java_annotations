package ua.yet.adv.java.annotation.services;

import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Service;

/**
 * Special service, which <code>init</code> method throws exception
 * 
 * @author Yuriy Tkach
 *
 */
@Service(name = "ThrowingService")
public class ThrowingService {

    /**
     * Init method that will throw exception which should be handled by service
     * loader
     * 
     * @throws Exception
     *             Some exception that is thrown
     */
    @Init(suppressException = true)
    public void init() throws Exception {
        throw new Exception("init exception");
    }

}
