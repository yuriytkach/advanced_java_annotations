package ua.yet.adv.java.annotation;

import lombok.extern.slf4j.Slf4j;

/**
 * Sample class to showcase JDK's build-in annotations
 * 
 * @author Yuriy Tkach
 */
@Slf4j
public class JavaBuildInAnnotations {

    @Deprecated
    private final int hello;

    public JavaBuildInAnnotations(@Deprecated int hello) {
        super();
        this.hello = hello;
    }

    public void someMethod(@Deprecated int haha) {
        log.info(String.valueOf(haha + hello));
    }

    @SafeVarargs
    public final void varargs(final Object... args) {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @FunctionalInterface
    public static interface MyFunction {
        public String sayHi();
    }

}
