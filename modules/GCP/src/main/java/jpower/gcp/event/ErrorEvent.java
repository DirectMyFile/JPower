package jpower.gcp.event;

import jpower.gcp.GCP;

public class ErrorEvent extends GCPEvent
{
   private int id;
   private String message;
   private Exception exception;

   protected ErrorEvent(GCP gcp)
   {
      super(gcp);
   }

   public ErrorEvent(GCP gcp, Exception exception)
   {
      super(gcp);
      this.exception = exception;
   }

   public ErrorEvent(GCP gcp, int id, String message)
   {
      super(gcp);
      this.id = id;
      this.message = message;
   }

   public int getID()
   {
      return id;
   }

   public String getMessage()
   {
      return message;
   }

   public Exception getException()
   {
      return exception;
   }
}
