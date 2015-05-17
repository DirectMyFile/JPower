package jpower.irc.events.abstraction;

/**
 * @author Logan Gorence
 */
public abstract class ChannelBasedEvent {

   private String channel;

   public ChannelBasedEvent(String channel) {
      this.channel = channel;
   }

   public String getChannel() {
      return channel;
   }
}
