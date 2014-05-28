package jpower.event;

import java.io.Serializable;

public class DeadEvent implements Serializable
{
   private final Object event;

   public DeadEvent(Object event)
   {
      this.event = event;
   }

   public Object getEvent()
   {
      return event;
   }

   public Class<?> getType()
   {
      return event.getClass();
   }
}
