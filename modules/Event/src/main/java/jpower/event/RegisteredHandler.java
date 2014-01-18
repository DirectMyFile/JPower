package jpower.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RegisteredHandler {
    private Object object;
    private List<RegisteredMethod> methods = new ArrayList<RegisteredMethod>();
    protected Class<? extends Annotation> annotationType;

    public RegisteredHandler(Object object) {
        this.object = object;
    }

    public RegisteredHandler setAnnotationType(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
        return this;
    }

    protected RegisteredHandler registerMethods() {
        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(annotationType)) {
                methods.add(new RegisteredMethod(method));
            }
        }
        return this;
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
