package jpower.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public class RegisteredHandler {
    private final Object object;
    private final Collection<RegisteredMethod> methods = new ArrayList<RegisteredMethod>();
    private Class<? extends Annotation> annotationType;

    public RegisteredHandler(Object object) {
        this.object = object;
    }

    public RegisteredHandler setAnnotationType(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
        return this;
    }

    RegisteredHandler registerMethods() {
        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(annotationType)) {
                methods.add(new RegisteredMethod(method));
            }
        }
        return this;
    }

    public boolean executeEvent(Object event) {
        boolean executed = false;
        for (RegisteredMethod method : methods) {
            if (method.getEventType().isAssignableFrom(event.getClass())) {
                method.invoke(object, event);
                executed = true;
            }
        }
        return executed;
    }

    public Object getObject() {
        return object;
    }
}
