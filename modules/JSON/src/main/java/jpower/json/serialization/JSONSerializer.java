package jpower.json.serialization;

import jpower.json.ObjectMapper;

import java.util.Collection;
import java.util.Map;

public class JSONSerializer
{
   private final JSONStyle style;

   public JSONSerializer(JSONStyle style)
   {
      this.style = style;
   }

   public String serialize(Object object)
   {
      if (object.getClass().isAssignableFrom(Map.class))
      {
         return serialize((Map) object);
      }
      else if (object.getClass().isAssignableFrom(Collection.class))
      {
         return null;
      }
      else
      {
         return serialize(ObjectMapper.create(object));
      }
   }

   public String serialize(Map<?, ?> map)
   {
      StringBuilder builder = new StringBuilder();
      builder.append('{');

      int count = 0;
      for (Object key : map.keySet())
      {
         count++;
         Object value = map.get(key);
         builder.append(style.getIndention())
                 .append('"')
                 .append(key.toString())
                 .append('"')
                 .append(':')
                 .append(' ')
                 .append('"')
                 .append(value.toString())
                 .append('"');
         if (count != map.keySet().size())
         {
            builder.append(',');
         }
         builder.append('\n');
      }
      builder.append('}');
      return builder.toString();
   }
}
