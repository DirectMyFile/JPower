package jpower.event;

/**
 * A Custom Event Bus Implementation
 */
public abstract class AbstractEventBus extends EventBus
{
   public AbstractEventBus()
   {
      super();
   }

   public AbstractEventBus(boolean globalEnabled)
   {
      super(globalEnabled);
   }
}
