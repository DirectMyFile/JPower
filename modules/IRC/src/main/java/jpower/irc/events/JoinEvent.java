package jpower.irc.events;

import jpower.irc.events.abstraction.ChannelBasedEvent;

public class JoinEvent extends ChannelBasedEvent {

   public JoinEvent(String channel) {
      super(channel);
   }
}
