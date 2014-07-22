package jpower.irc;

public class TopicChangeEvent
{

   private Channel channel;
   private String oldTopic;
   private String newTopic;

   public TopicChangeEvent(Channel channel, String oldTopic, String newTopic)
   {
      this.channel = channel;
      this.oldTopic = oldTopic;
      this.newTopic = newTopic;
   }

   public Channel getChannel()
   {
      return channel;
   }

   public String getOldTopic()
   {
      return oldTopic;
   }

   public String getNewTopic()
   {
      return newTopic;
   }
}
