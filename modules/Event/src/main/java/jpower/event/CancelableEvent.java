package jpower.event;

/**
 * A base class for events that are cancelable (implements Cancelable)
 */
public abstract class CancelableEvent implements Cancelable
{
   private boolean canceled;

   @Override
   public void setCanceled(boolean cancel)
   {
      this.canceled = cancel;
   }

   @Override
   public boolean isCanceled()
   {
      return canceled;
   }
}
