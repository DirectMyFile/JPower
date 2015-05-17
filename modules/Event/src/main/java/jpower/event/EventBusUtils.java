package jpower.event;

/**
 * Event Bus Utilities
 */
public class EventBusUtils {
   /**
    * Registers all handlers for the specified event bus.
    *
    * @param bus      Event Bus
    * @param handlers event handlers
    */
   public static void registerAll(IEventBus bus, Object... handlers) {
      for (Object handler : handlers) {
         bus.register(handler);
      }
   }

   /**
    * Unregisters all handlers for the specified event bus.
    *
    * @param bus      Event Bus
    * @param handlers event handlers
    */
   public static void unregisterAll(IEventBus bus, Object... handlers) {
      for (Object handler : handlers) {
         bus.unregister(handler);
      }
   }
}
