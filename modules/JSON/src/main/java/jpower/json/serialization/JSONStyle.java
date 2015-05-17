package jpower.json.serialization;

public class JSONStyle {
   private String indention = "    ";
   private boolean singleQuotes = false;

   public JSONStyle() {
   }

   public static JSONStyle defaultStyle() {
      return new JSONStyle();
   }

   public boolean isSingleQuotes() {
      return singleQuotes;
   }

   public void setSingleQuotes(boolean singleQuotes) {
      this.singleQuotes = singleQuotes;
   }

   public String getIndention() {
      return indention;
   }

   public void setIndention(String indention) {
      this.indention = indention;
   }
}
