package jpower.core.reflect;

import jpower.core.annotation.Incomplete;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Invokes Methods in Certain Fashions
 */
@Incomplete
public class MethodInvoker {
    private Object object;
    private Class<?> clazz;

    public MethodInvoker(Object object) {
        this.object = object;
        this.clazz = object.getClass();
    }

    public MethodInvoker(Class<?> clazz) {
        this.clazz = clazz;
        this.object = null;
    }

    public Object invokeMethod(String name, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?>[] paramTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = args[i].getClass();
        }
        Method method = clazz.getMethod(name, paramTypes);
        return method.invoke(object, args);
    }
}