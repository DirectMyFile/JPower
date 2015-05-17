package jpower.irc.events;

public class PingEvent {

   private String trail;

   public PingEvent(String trail) {
      this.trail = trail;
   }

   public String getTrail() {
      return trail;
   }

}
