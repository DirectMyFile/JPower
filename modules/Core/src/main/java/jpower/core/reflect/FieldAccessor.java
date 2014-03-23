package jpower.core.reflect;

import java.lang.reflect.Field;

public class FieldAccessor {
    private final Object object;
    private final Class<?> clazz;

    public FieldAccessor(Class<?> clazz) {
        this.clazz = clazz;
        this.object = null;
    }

    public FieldAccessor(Object object) {
        this.object = object;
        this.clazz = object.getClass();
    }

    public Field field(String name) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(name);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }

    public Object get(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return field(fieldName).get(object);
    }

    public void set(String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        field(fieldName).set(object, value);
    }
}
