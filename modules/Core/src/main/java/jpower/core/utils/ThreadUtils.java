package jpower.core.utils;

public class ThreadUtils {
    /**
     * Sleeps without Exceptions
     *
     * @param time time to sleep in milliseconds
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
            // Do Nothing
        }
    }

    public static Thread start(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }

    public static Thread startDaemon(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        return thread;
    }
}
