package jpower.irc.events;

import java.util.List;

public class MotdEvent
{

   private List<String> motd;

   public MotdEvent(List<String> motd)
   {
      this.motd = motd;
   }

   public List<String> getMOTD()
   {
      return motd;
   }

}
