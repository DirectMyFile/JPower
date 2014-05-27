package jpower.json;

public class JSONNull
{
   public static JSONNull NULL = new JSONNull();

   private JSONNull()
   {
   }

   @Override
   public String toString()
   {
      return "null";
   }
}
