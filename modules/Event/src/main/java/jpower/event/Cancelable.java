package jpower.event;

public interface Cancelable {
   boolean isCanceled();

   void setCanceled(boolean cancel);
}
