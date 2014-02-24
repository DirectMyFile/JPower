package jpower.core.utils;

import java.util.Collections;
import java.util.List;

public class ListUtils {
    public static <T> List<T> singleton(T single) {
        return Collections.singletonList(single);
    }
}
