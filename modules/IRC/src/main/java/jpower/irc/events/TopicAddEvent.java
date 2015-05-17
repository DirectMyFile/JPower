package jpower.irc.events;

import jpower.irc.Channel;

public class TopicAddEvent {

   private Channel channel;
   private String topic;

   public TopicAddEvent(Channel channel, String topic) {
      this.channel = channel;
      this.topic = topic;
   }

   public Channel getChannel() {
      return channel;
   }

   public String getTopic() {
      return topic;
   }
}
