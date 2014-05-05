package jpower.json;

import jpower.json.serialization.JSONSerializer;
import jpower.json.serialization.JSONStyle;

import java.io.IOException;

public class JSON
{

   public String serialize(Object object)
   {
      JSONSerializer serializer = new JSONSerializer(JSONStyle.defaultStyle());
      try
      {
         return serializer.serialize(object);
      } catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }

}
