package jpower.core.utils;

import jpower.core.reflect.MethodInvoker;

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

   public static Thread start(Runnable action) {
      Thread thread = new Thread(action);
      thread.start();
      return thread;
   }

   public static Thread startDaemon(Runnable action) {
      Thread thread = new Thread(action);
      thread.setDaemon(true);
      thread.start();
      return thread;
   }

   public static Thread[] getAllThreads() {
      return (Thread[]) MethodInvoker.invokeSafe(Thread.class, "getThreads");
   }
}
