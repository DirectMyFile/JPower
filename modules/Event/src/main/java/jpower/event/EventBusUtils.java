package jpower.event;

public class EventBusUtils
{
   public static void registerAll(IEventBus bus, Object... handlers)
   {
      for (Object handler : handlers)
      {
         bus.register(handler);
      }
   }
}
