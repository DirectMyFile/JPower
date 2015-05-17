package jpower.irc.events;

import java.util.Map;

public class ServerSupportsEvent {

   private Map<String, Object> supports;

   public ServerSupportsEvent(Map<String, Object> supports) {
      this.supports = supports;
   }

   public Map<String, Object> getSupports() {
      return supports;
   }
}
