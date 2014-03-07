package jpower.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericSniffer<T> {
    public Type sniff() {
        Type superclass = getClass().getGenericSuperclass();
        return ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }

    @Override
    public String toString() {
        return sniff().getTypeName();
    }
}
