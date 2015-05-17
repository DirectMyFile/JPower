package jpower.event;

import java.io.Serializable;

/**
 * Represents an Event that did not get handled by any handlers
 */
public class DeadEvent implements Serializable {
   private final Object event;

   /**
    * Creates a new DeadEvent
    *
    * @param event event
    */
   public DeadEvent(Object event) {
      this.event = event;
   }

   /**
    * Gets the Event Object
    *
    * @return event object
    */
   public Object getEvent() {
      return event;
   }

   /**
    * Gets the Event Type
    *
    * @return event class type
    */
   public Class<?> getType() {
      return event.getClass();
   }
}
