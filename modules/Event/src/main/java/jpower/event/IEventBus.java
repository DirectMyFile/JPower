package jpower.event;

/**
 * Base Interface for Event Bus Implementations
 */
public interface IEventBus
{
   void register(final Object handler);

   boolean unregister(final Object handler);

   void post(final Object event);
}
