package jpower.json.serialization;

public class JSONStyle
{
   private String indention = "    ";

   public JSONStyle()
   {
   }

   public void setIndention(String indention)
   {
      this.indention = indention;
   }

   public String getIndention()
   {
      return indention;
   }

   public static JSONStyle defaultStyle()
   {
      return new JSONStyle();
   }
}
