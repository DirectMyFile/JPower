package jpower.irc;

import java.util.List;

public class MultiClientHandler
{

   private List<Network> networks;

   public MultiClientHandler(List<Network> networks)
   {
      this.networks = networks;
   }

   public List<Network> getNetworks()
   {
      return networks;
   }

   public void connectAll()
   {
      for (Network n : networks)
      {
         n.connect();
      }
   }

}
