package jpower.event;

import jpower.core.Wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class EventBus implements IEventBus
{
   protected final List<RegisteredHandler> handlers;
   protected final boolean globalEnabled;

   /**
    * {@inheritDoc}
    */
   public EventBus()
   {
      this(true);
   }

   /**
    * {@inheritDoc}
    */
   public EventBus(final boolean globalEnabled)
   {
      handlers = new ArrayList<>();
      this.globalEnabled = globalEnabled;
   }

   /**
    * {@inheritDoc}
    */
   public void register(final Object object)
   {
      handlers.add(new RegisteredHandler(object).setAnnotationType(EventHandler.class).registerMethods());
   }

   /**
    * {@inheritDoc}
    */
   public boolean unregister(final Object object)
   {
      RegisteredHandler handlerToRemove = null;
      for (RegisteredHandler handler : handlers)
      {
         if (handler.getObject() == object)
         {
            handlerToRemove = handler;
         }
      }
      if (handlerToRemove == null)
      {
         return false;
      }
      handlers.remove(handlerToRemove);
      return true;
   }

   /**
    * {@inheritDoc}
    */
   public void post(final Object event)
   {
      Wrapper<Boolean> didRun = new Wrapper<>(false);
      handlers.forEach(handler -> {
         if (handler.executeEvent(event))
         {
            didRun.set(true);
         }
      });
      //noinspection InstanceofThis
      if (!DeadEvent.class.isAssignableFrom(event.getClass()) && !didRun.get() && !(this instanceof GlobalEventBus))
      {
         post(new DeadEvent(event));
      }
      if (!DeadEvent.class.isAssignableFrom(event.getClass()) && globalEnabled)
      {
         GlobalEventBus.get().post(event);
      }
   }
}
