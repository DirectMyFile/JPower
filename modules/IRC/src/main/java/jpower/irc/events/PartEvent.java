package jpower.irc.events;

import jpower.irc.events.abstraction.ChannelBasedEvent;

public class PartEvent extends ChannelBasedEvent {

   public PartEvent(String channel) {
      super(channel);
   }
}
