package jpower.core;

/**
 * A Service is an entity that can be started and stopped.
 */
public interface Service {
   /**
    * Start the Service
    */
   void start();

   /**
    * Stop the Service
    */
   void stop();

   /**
    * Restart the Service
    */
   default void restart() {
      stop();
      start();
   }
}
