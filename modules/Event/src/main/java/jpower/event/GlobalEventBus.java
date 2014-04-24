package jpower.event;

/**
 * The Global Event Bus.
 * <p>Essentially this provides a way to intercept events from all Event Buses, except ones that opt out.</p>
 */
@SuppressWarnings("Singleton")
public class GlobalEventBus extends EventBus
{
   private static final GlobalEventBus INSTANCE;

   static
   {
      INSTANCE = new GlobalEventBus();
   }

   private GlobalEventBus()
   {
      super(false);
   }

   public static GlobalEventBus get()
   {
      return INSTANCE;
   }
}
