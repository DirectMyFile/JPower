package jpower.event;

/**
 * {@inheritDoc}
 */
public abstract class AbstractEventBus extends EventBus
{
   /**
    * {@inheritDoc}
    */
   public AbstractEventBus()
   {
      super();
   }

   /**
    * {@inheritDoc}
    */
   public AbstractEventBus(boolean globalEnabled)
   {
      super(globalEnabled);
   }
}
