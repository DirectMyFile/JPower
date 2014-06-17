package jpower.event;

import jpower.core.Wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Implementation of the JPower Event Bus
 */
public class EventBus implements IEventBus
{
   /**
    * The Registered Handlers
    */
   protected final List<RegisteredHandler> handlers;

   /**
    * Creates a new Event Bus
    */
   public EventBus()
   {
      handlers = new ArrayList<>();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void register(final Object object)
   {
      handlers.add(new RegisteredHandler(object).setAnnotationType(EventHandler.class).registerMethods());
   }

   /**
    * {@inheritDoc}
    */
   @Override
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
   @Override
   public void post(final Object event)
   {
      Wrapper<Boolean> didRun = new Wrapper<>(false);
      handlers.forEach(handler -> {
         if (handler.executeEvent(event))
         {
            didRun.set(true);
         }
      });

      if (!DeadEvent.class.isAssignableFrom(event.getClass()) && !didRun.get())
      {
         post(new DeadEvent(event));
      }
   }
}
