package jpower.core.utils;

public class StringUtils {
    public static String random(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = CharUtils.random();
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
}
