package jpower.json.serialization;

public class JSONStyle
{
   private String indention = "    ";
   private boolean singleQuotes = false;

   public JSONStyle()
   {
   }

   public void setIndention(String indention)
   {
      this.indention = indention;
   }

   public void setSingleQuotes(boolean singleQuotes)
   {
      this.singleQuotes = singleQuotes;
   }

   public boolean isSingleQuotes()
   {
      return singleQuotes;
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
