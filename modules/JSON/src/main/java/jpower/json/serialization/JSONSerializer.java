package jpower.json.serialization;

import jpower.core.out.IndentPrinter;
import jpower.json.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;

public class JSONSerializer
{
   private final JSONStyle style;

   public JSONSerializer(JSONStyle style)
   {
      this.style = style;
   }

   public String serialize(Object object) throws IOException
   {
      if (Map.class.isAssignableFrom(object.getClass()))
      {
         return serialize((Map) object);
      }
      else if (Collection.class.isAssignableFrom(object.getClass()))
      {
         return serialize((Collection<?>) object);
      }
      else
      {
         return serialize(ObjectMapper.create(object));
      }
   }

   public String serialize(Collection<?> list) throws IOException
   {
      StringWriter writer = new StringWriter();
      IndentPrinter out = new IndentPrinter(style.getIndention(), writer);
      out.write('[');

      int count = 0;
      for (Object entry : list)
      {
         count++;
         toJSON(entry, out);
         if (count != list.size())
         {
            out.write(',');
         }
      }

      out.write(']');
      out.close();
      return writer.toString();
   }

   public String serialize(Map<?, ?> map) throws IOException
   {
      StringWriter writer = new StringWriter();
      IndentPrinter out = new IndentPrinter(style.getIndention(), writer);
      out.write('{');

      int count = 0;
      for (Object key : map.keySet())
      {
         count++;
         Object value = map.get(key);
         out.write((style.isSingleQuotes() ? '\'' : '"'));
         out.print(key.toString());
         out.write((style.isSingleQuotes() ? '\'' : '"'));
         out.write(':');
         toJSON(value, out);
         if (count != map.keySet().size())
         {
            out.write(',');
         }
      }
      out.write('}');
      out.close();
      return writer.toString();
   }

   public void toJSON(Object value, IndentPrinter out) throws IOException
   {
      if (value == null)
      {
         out.print("null");
      }
      else if (Number.class.isAssignableFrom(value.getClass()))
      {
         out.print(toJSON((Number) value));
      }
      else if (String.class.isAssignableFrom(value.getClass()))
      {
         out.print(toJSON((String) value, style));
      }
      else
      {
         out.print(serialize(value));
      }
   }

   public static String toJSON(Number number)
   {
      return number.toString();
   }

   public static String toJSON(String str, JSONStyle style)
   {
      return (style.isSingleQuotes() ? '\'' : '"') + str + (style.isSingleQuotes() ? '\'' : '"');
   }
}
