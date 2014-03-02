package jpower.core.reflect;

public interface InvokeListener {
    public void before();
    public void error(Exception e);
    public void after(Object returned);

    public static InvokeListener fake() {
        return new InvokeListener() {
            @Override
            public void before() {

            }

            @Override
            public void error(Exception e) {

            }

            @Override
            public void after(Object returned) {

            }
        };
    }
}
