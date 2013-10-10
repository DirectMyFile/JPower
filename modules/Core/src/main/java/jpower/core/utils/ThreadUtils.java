package jpower.core.utils;

public class ThreadUtils {
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
            // Do Nothing
        }
    }
}
