package jpower.core.utils;

import java.util.Collections;
import java.util.List;

public class ListUtils {
    public static <T> List<T> singleton(T single) {
        return Collections.singletonList(single);
    }

    public static String toString(List<String> lines) {
        return join(lines, System.lineSeparator());
    }

    public static String join(List<String> inputs, String joinBy) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputs.size(); i++) {
            if (i != 0 && i != inputs.size() - 1)
                builder.append(joinBy);
            builder.append(inputs.get(i));
        }
        return builder.toString();
    }
}
