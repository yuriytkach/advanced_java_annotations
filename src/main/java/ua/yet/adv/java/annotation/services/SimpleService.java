package ua.yet.adv.java.annotation.services;

import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Note;
import ua.yet.adv.java.annotation.Service;

/**
 * Service that will be loaded by loader right away. Contains several init
 * methods, some of which will be called.
 * 
 * @author Yuriy Tkach
 *
 */
@Service(name = "Simple service")
@Note("This is an interesting service")
@Note("I bet you've never seen something like that :)")
public class SimpleService {

    /**
     * Private field to showcase the impossibility of changing primitive private
     * fields with reflection
     */
    private int inits = 0;

    /**
     * Init method that will be called since it contains no arguments
     */
    @Init
    public void initService() {
        System.out.println("Initializing...");
        inits++;
    }

    /**
     * Init method that won't be called, because it contains arguments
     * 
     * @param arg
     *            Some argument
     */
    @Init
    public void initServiceArgs(int arg) {
        System.out.println("Initializing with args ...");
    }

    /**
     * Private init method that will be called showing how to do that will
     * reflection
     */
    @Init
    private void privateInit() {
        System.out.println("Initializing in private..." + inits);
    }

    /**
     * Just some other method
     */
    public void someOtherMethod() {

    }

}
