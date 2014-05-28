package jpower.core;

/**
 * A Service is a thing that can be started and stopped.
 */
public interface Service
{
   /**
    * Starts the Service
    */
   void start();

   /**
    * Stops the Service
    */
   void stop();

   /**
    * Restarts the Service
    */
   default void restart()
   {
      stop();
      start();
   }
}
