package jpower.core;

public class Timer
{

   private long startTime;
   private long endTime;
   private boolean running;

   /**
    * Start the Timer
    */
   public void start()
   {
      if (running)
      {
         return;
      }
      running = true;
      startTime = System.currentTimeMillis();
   }

   /**
    * Stop the Timer
    */
   public void stop()
   {
      if (!running)
      {
         return;
      }
      running = false;
      endTime = System.currentTimeMillis();
   }

   /**
    * Reset the Timer
    */
   public void reset()
   {
      running = false;
      startTime = 0;
      endTime = 0;
   }

   /**
    * Get the length of time that the Timer was running
    * @return time in milliseconds
    */
   public long getTime()
   {
      if (running)
      {
         return System.currentTimeMillis() - startTime;
      }
      return endTime - startTime;
   }

   /**
    * Time a Runnable task and return the Timer instance
    * @return timer for length of operation
    */
   public static Timer of(Runnable task)
   {
      Timer timer = new Timer();
      timer.start();
      task.run();
      timer.stop();
      return timer;
   }

   /**
    * Time a Runnable task and return the length of time it took.
    * @return length of time task took
    */
   public static long timeOf(Runnable task)
   {
      return of(task).getTime();
   }
}
