package jpower.core;

/**
 * A ManagedThread is a Thread that is optimized to allow easy stopping.
 *
 * <p>First the Thread specifies the code to run</p>
 */
public class ManagedThread extends Thread
{
   private final Runnable action;

   public ManagedThread(Runnable action)
   {
      this.action = action;
   }

   @Override
   public void run()
   {
      action.run();
   }
}
