package jpower.event;

public interface Cancelable
{
   void setCanceled(boolean cancel);

   boolean isCanceled();
}
