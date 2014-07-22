package jpower.irc;

public class InviteEvent
{

   private User sender;
   private String channel;

   public InviteEvent(User sender, String channel)
   {
      this.sender = sender;
      this.channel = channel;
   }

   public User getSender()
   {
      return sender;
   }

   public String getChannel()
   {
      return channel;
   }

}
