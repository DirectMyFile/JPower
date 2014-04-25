package jpower.core.internal;

import jpower.core.Task;

import java.util.Collection;
import java.util.HashSet;

/**
 * TODO: Port this to use a lightweight system
 */
public class CancelStateTracker
{
   private static final Collection<Task> states = new HashSet<>();

   public static void setCanceled(Task task, boolean cancel)
   {
      if (cancel)
      {
         states.add(task);
      } else
      {
         states.remove(task);
      }
   }

   public static boolean isCanceled(Task task)
   {
      return states.contains(task);
   }
}
