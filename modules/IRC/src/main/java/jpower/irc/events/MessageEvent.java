package jpower.irc.events;

import jpower.irc.Sendable;

public class MessageEvent
{

   private Sendable sender;
   private Sendable target;
   private String message;

   public MessageEvent(Sendable sender, Sendable target, String message)
   {
      this.sender = sender;
      this.target = target;
      this.message = message;
   }

   public Sendable getSender()
   {
      return sender;
   }

   public Sendable getTarget()
   {
      return target;
   }

   public String getMessage()
   {
      return message;
   }

}
