package jpower.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RegisteredHandler {
    private Object object;
    private List<RegisteredMethod> methods = new ArrayList<RegisteredMethod>();

    public RegisteredHandler(Object object) {
        this.object = object;
        registerMethods();
    }

    private void registerMethods() {
        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                methods.add(new RegisteredMethod(method));
            }
        }
    }

    public void executeEvent(Object event) {
        for (RegisteredMethod method : methods) {
            if (method.getEventType().isAssignableFrom(event.getClass())) {
                method.invoke(object, event);
            }
        }
    }

    public Object getObject() {
        return object;
    }
}
