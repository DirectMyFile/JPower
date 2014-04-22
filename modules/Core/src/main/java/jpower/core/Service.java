package jpower.core;

public interface Service
{
   void start();

   void stop();

   default void restart()
   {
      stop();
      start();
   }
}
