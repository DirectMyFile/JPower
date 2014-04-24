package jpower.core;

public class Timer
{

   private long startTime;
   private long endTime;
   private boolean running;

   public void start()
   {
      if (running) return;
      running = true;
      startTime = System.currentTimeMillis();
   }

   public void stop()
   {
      if (!running) return;
      running = false;
      endTime = System.currentTimeMillis();
   }

   public void reset()
   {
      running = false;
      startTime = 0;
      endTime = 0;
   }

   public long getTime()
   {
      if (running) return System.currentTimeMillis() - startTime;
      return endTime - startTime;
   }
}
