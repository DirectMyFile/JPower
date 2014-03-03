package jpower.core.reflect;

public interface InvokeListener {
    void before();
    void error(Exception e);
    void after(Object returned);

    static InvokeListener fake() {
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
