package jpower.irc;

import java.io.Serializable;

public class JoinEvent
{

   public String channel;

   public JoinEvent(String channel)
   {
      this.channel = channel;
   }

}
