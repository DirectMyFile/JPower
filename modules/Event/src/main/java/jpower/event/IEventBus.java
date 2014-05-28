package jpower.event;

/**
 * Base Interface for Event Bus Implementations
 */
public interface IEventBus
{
   /**
    * Registers an Event Handler
    *
    * @param handler handler to register
    */
   void register(final Object handler);

   /**
    * Unregisters an Event Handler
    *
    * @param handler handler to unregister
    * @return if the event handler was unregistered
    */
   boolean unregister(final Object handler);

   /**
    * Executes an Event
    *
    * @param event event to execute
    */
   void post(final Object event);
}
