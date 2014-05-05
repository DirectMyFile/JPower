package jpower.json;

import jpower.json.serialization.JSONSerializer;
import jpower.json.serialization.JSONStyle;

import java.io.IOException;

public class JSON
{

   private final JSONStyle style;

   private JSONSerializer serializer;

   public JSON()
   {
      style = JSONStyle.defaultStyle();
   }

   public JSON(JSONStyle style)
   {
      this.style = style;
   }

   public static JSON create()
   {
      return new JSON();
   }

   public static JSON create(JSONStyle style)
   {
      return new JSON(style);
   }

   public String serialize(Object object)
   {
      createIfNeeded(false, true);
      try
      {
         return serializer.serialize(object);
      } catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }

   private void createIfNeeded(boolean createParser, boolean createSerializer)
   {
      if (createSerializer)
      {
         serializer = new JSONSerializer(style);
      }
   }
}
