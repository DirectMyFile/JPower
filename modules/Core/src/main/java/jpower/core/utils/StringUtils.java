package jpower.core.utils;

import java.util.List;

public class StringUtils {
    public static String random(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = CharUtils.randomUppercase();
        }
        return new String(chars);
    }

    public static String[] random(int stringLength, int count) {
        String[] strings = new String[count];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = random(stringLength);
        }
        return strings;
    }

    public static List<String> tokenize(String input, String regex) {
        return ArrayUtils.toList(input.split(regex));
    }
}
