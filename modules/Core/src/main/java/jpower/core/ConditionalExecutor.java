package jpower.core;

import jpower.core.utils.ThreadUtils;

import java.util.concurrent.TimeUnit;

/**
 * Utility to run a task with multiple different
 * methods to check for conditions while running
 * that task.
 */
public class ConditionalExecutor
{

   private final Runnable task;

   public ConditionalExecutor(Runnable task)
   {
      this.task = task;
   }

   public void until(Condition condition)
   {
      while (condition.inverted())
      {
         task.run();
      }
   }

   public void when(Condition condition)
   {
      while (condition.check())
      {
         task.run();
      }
   }

   public void intervalUntil(long amount, TimeUnit unit, Condition condition)
   {
      while (!condition.check())
      {
         task.run();
         ThreadUtils.sleep(unit.toMillis(amount));
      }
   }

   public void intervalWhen(long amount, TimeUnit unit, Condition condition)
   {
      while (condition.check())
      {
         task.run();
         ThreadUtils.sleep(unit.toMillis(amount));
      }
   }
}
