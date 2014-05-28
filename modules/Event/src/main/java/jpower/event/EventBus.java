package jpower.event;

import jpower.core.Wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Annotation-based Event Bus
 */
public class EventBus implements IEventBus
{
   protected final List<RegisteredHandler> handlers;
   protected final boolean globalEnabled;

   public EventBus()
   {
      this(true);
   }

   public EventBus(final boolean globalEnabled)
   {
      handlers = new ArrayList<>();
      this.globalEnabled = globalEnabled;
   }

   /**
    * Register an Event Handler
    *
    * @param object Object
    */
   public void register(final Object object)
   {
      handlers.add(new RegisteredHandler(object).setAnnotationType(EventHandler.class).registerMethods());
   }

   /**
    * Unregister an Event Handler
    *
    * @param object Object
    * @return handler was removed
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
    * Post an Event to the Bus
    *
    * @param event Event to Post
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
      if (!event.getClass().isAssignableFrom(DeadEvent.class) && !didRun.get() && !(this instanceof GlobalEventBus))
      {
         post(new DeadEvent(event));
      }
      if (!event.getClass().isAssignableFrom(DeadEvent.class) && globalEnabled)
      {
         GlobalEventBus.get().post(event);
      }
   }
}
