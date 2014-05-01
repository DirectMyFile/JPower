package jpower.gcp;

public enum GCPCommands implements GCPCommand
{
   JOIN
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "Channel",
                         "Pass",
                         "User"
                 };
              }
           },
   LEAVE
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "Channel",
                         "User",
                         "Reason"
                 };
              }
           },
   MSG
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "Channel",
                         "Target",
                         "Message"
                 };
              }
           },
   PING
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "User"
                 };
              }
           },
   PONG
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "User"
                 };
              }
           },
   CONNECT
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "Nick",
                         "Pass",
                         "RealName"
                 };
              }
           },
   QUIT
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "Reason"
                 };
              }
           },
   REGISTER
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "Type",
                         "Channel",
                         "Pass"
                 };
              }
           },
   ERROR
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "Error",
                         "Type",
                         "Channel",
                         "Target"
                 };
              }
           },
   SYNTAXERROR
           {
              @Override
              public String[] opts()
              {
                 return new String[]{
                         "Error"
                 };
              }
           };

   @Override
   public String toString()
   {
      return name();
   }
}
