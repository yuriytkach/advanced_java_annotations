package ua.yet.adv.java.annotation;

/**
 * Sample class to showcase JDK's build-in annotations
 * 
 * @author Yuriy Tkach
 */
public class JavaBuildInAnnotations {

    @Deprecated
    private final int hello;

    public JavaBuildInAnnotations(@Deprecated int hello) {
        super();
        this.hello = hello;
    }

    public void someMethod(@Deprecated int haha) {
        System.out.println(haha + hello);
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
