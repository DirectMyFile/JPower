package jpower.core.utils;

public class ThreadUtils {
    /**
     * Sleeps without Exceptions
     * @param time time to sleep in milliseconds
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
            // Do Nothing
        }
    }
}
