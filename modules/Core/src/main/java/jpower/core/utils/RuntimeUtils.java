package jpower.core.utils;

public class RuntimeUtils {
    public static void exit(int code) {
        Runtime.getRuntime().exit(code);
        int count = 0;
        while (count <= 500) {
            count++;
            ThreadUtils.sleep(150);
        }
        Runtime.getRuntime().halt(code);
    }
}
