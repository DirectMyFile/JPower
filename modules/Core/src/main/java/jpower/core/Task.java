package jpower.core;

import jpower.core.internal.CancelStateTracker;

@FunctionalInterface
public interface Task extends Runnable
{

   /**
    * Used to run in Threads
    */
   @Override
   default void run()
   {
      execute();
   }

   /**
    * Executes this Task
    */
   void execute();

   /**
    * Checks if this task is canceled
    *
    * @return Task is Canceled
    */
   default boolean isCanceled()
   {
      return CancelStateTracker.isCanceled(this);
   }

   /**
    * Cancels the Task
    */
   default void cancel()
   {
      CancelStateTracker.setCanceled(this, true);
   }

   /**
    * Actives the Task if it is canceled
    */
   default void activate()
   {
      CancelStateTracker.setCanceled(this, false);
   }
}
