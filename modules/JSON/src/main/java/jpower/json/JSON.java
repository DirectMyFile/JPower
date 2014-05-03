package jpower.json;

import jpower.json.serialization.JSONSerializer;
import jpower.json.serialization.JSONStyle;

public class JSON
{

   public String serialize(Object object)
   {
      JSONSerializer serializer = new JSONSerializer(JSONStyle.defaultStyle());
      return serializer.serialize(object);
   }

}
