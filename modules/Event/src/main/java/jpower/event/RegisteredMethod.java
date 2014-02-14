package jpower.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class RegisteredMethod {
    private final Method method;

    public RegisteredMethod(Method method) {
        this.method = method;
    }

    public void invoke(Object instance, Object event) {
        try {
            method.invoke(instance, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Class<?> getEventType() {
        return method.getParameterTypes()[0];
    }
}
