package jpower.core;

public class ParseException extends RuntimeException
{
   protected int line = -1;
   protected int column = -1;
   protected final String parseMessage;

   public ParseException(int line, int column, String message)
   {
      this.line = line;
      this.column = column;
      this.parseMessage = message;
   }

   public ParseException(int line, String message)
   {
      this.line = line;
      this.parseMessage = message;
   }

   @Override
   public String getMessage()
   {
      return parseMessage + " (at line " + line + (column != -1 ? ", column " + column : "") + ")";
   }
}
