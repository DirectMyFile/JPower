package jpower.gcp;

public enum GCPCommand
{
   JOIN, LEAVE, QUIT, MSG, PING, PONG, CONNECT, REGISTER, ERROR, SYNTAXERROR;

   @Override
   public String toString()
   {
      return name();
   }
}
