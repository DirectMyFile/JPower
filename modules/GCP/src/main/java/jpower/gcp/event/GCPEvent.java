package jpower.gcp.event;

import jpower.gcp.GCP;

public class GCPEvent
{
   private final GCP gcp;

   public GCPEvent(GCP gcp)
   {
      this.gcp = gcp;
   }

   public GCP getGcp()
   {
      return gcp;
   }
}
