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
         builder.append('\n');
         count++;
         Object value = map.get(key);
         builder.append(style.getIndention())
                 .append('"')
                 .append(key.toString())
                 .append('"')
                 .append(':')
                 .append(' ');
         if (value.getClass().isAssignableFrom(Integer.class)) {
            builder.append(toJSON((int) value));
         } else if (value.getClass().isAssignableFrom(Long.class)) {
            builder.append(toJSON((long) value));
         } else if (value.getClass().isAssignableFrom(String.class)) {
            builder.append(toJSON((String) value, style));
         } else {
            builder.append(serialize(value));
         }
         if (count != map.keySet().size())
         {
            builder.append(',');
         }
         else
         {
            builder.append('\n');
         }
      }
      builder.append('}');
      return builder.toString();
   }

   public static String toJSON(int number) {
      return Integer.toString(number);
   }

   public static String toJSON(long number) {
      return Long.toString(number);
   }

   public static String toJSON(String str, JSONStyle style) {
      return (style.isSingleQuotes() ? '\'' : '"') + str + (style.isSingleQuotes() ? '\'' : '"');
   }
}
