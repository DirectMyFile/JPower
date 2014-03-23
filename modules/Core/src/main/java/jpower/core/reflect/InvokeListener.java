package jpower.core.reflect;

public interface InvokeListener {
    void before();
    void after(Object returned);
    void error(Exception e);

    static InvokeListener ignore() {
        return new InvokeListener() {
            @Override
            public void before() {

            }

            @Override
            public void after(Object returned) {

            }

            @Override
            public void error(Exception e) {

            }
        };
    }
}
