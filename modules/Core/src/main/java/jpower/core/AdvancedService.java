package jpower.core;

/**
 * A Service that can throw exceptions.
 */
public interface AdvancedService {
   void start() throws Exception;

   void stop() throws Exception;

   default void restart() throws Exception {
      stop();
      start();
   }
}
