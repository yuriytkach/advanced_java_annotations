package ua.yet.adv.java.annotation;

@SuppressWarnings("unused")
public class JavaBuildInAnnotations {

	@Deprecated
	private final int hello;

	public JavaBuildInAnnotations(@Deprecated int hello) {
		super();
		this.hello = hello;
	}

	public void someMethod(@Deprecated int haha) {

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
