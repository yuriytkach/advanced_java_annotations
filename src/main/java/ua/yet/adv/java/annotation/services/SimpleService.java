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
     * Private field to showcase the possibility of changing private fields with
     * reflection
     */
    private int inits = 0;

    /**
     * Private final field to showcase the impossibility of changing final
     * primitive fields with reflection
     */
    private final int CONST = 10;

    /**
     * Init method that will be called since it contains no arguments
     */
    @Init
    public void initService() {
        inits++;
        
        System.out.println(
                "  >> Initializing in public init... (inits="
                        + inits + ", const=" + CONST + ")");
    }

    /**
     * Init method that won't be called, because it contains arguments
     * 
     * @param arg
     *            Some argument
     */
    @Init
    public void initServiceArgs(int arg) {
        inits++;
        System.out.println("  >> Initializing with args ...");
    }

    /**
     * Private init method that will be called showing how to do that will
     * reflection
     */
    @Init
    private void privateInit() {
        inits++;
        System.out.println(
                "  >> Initializing in private init... (inits="
                        + inits + ", const=" + CONST + ")");
    }

    /**
     * Just some other method
     */
    public void someOtherMethod() {

    }

}
