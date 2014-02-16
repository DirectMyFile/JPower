package jpower.core.utils;

import jpower.core.Wrapper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

public class ArrayUtils {
    public static boolean containsDuplicates(Object[] objects) {
        Collection<Object> list = new LinkedList<>();
        Wrapper<Boolean> duplicates = new Wrapper<>(false);
        Stream.of(objects).forEach(obj -> {
            if (list.contains(obj)) {
                duplicates.set(true);
            } else {
                list.add(obj);
            }
        });
        return duplicates.get();
    }

    public static int count(Object obj, Object[] in) {
        Wrapper<Integer> count = new Wrapper<>(0);
        Stream.of(in).forEach(item -> {
            if (item == obj) {
                count.set(count.get() + 1);
            }
        });
        return count.get();
    }
}
