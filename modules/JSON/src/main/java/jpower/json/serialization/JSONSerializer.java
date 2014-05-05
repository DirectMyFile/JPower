package jpower.json.serialization;

import jpower.core.out.IndentPrinter;
import jpower.json.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
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

   public String serialize(List<?> list) throws IOException
   {
      StringWriter writer = new StringWriter();
      IndentPrinter out = new IndentPrinter(style.getIndention(), writer);
      out.write('[');
      out.println();
      out.increment();

      for (Object entry : list)
      {
         out.printIndent();
         toJSON(entry, out);
         out.println();
      }
      out.decrement();
      out.write(']');
      out.close();
      return writer.toString();
   }

   public String serialize(Map<?, ?> map) throws IOException
   {
      StringWriter writer = new StringWriter();
      IndentPrinter out = new IndentPrinter(style.getIndention(), writer);
      out.write('{');

      out.increment();
      int count = 0;
      for (Object key : map.keySet())
      {
         out.println();
         out.printIndent();
         count++;
         Object value = map.get(key);
         out.write('"');
         out.print(key.toString());
         out.write('"');
         out.write(':');
         out.write(' ');
         toJSON(value, out);
         if (count != map.keySet().size())
         {
            out.write(',');
         }
         else
         {
            out.println();
         }
      }
      out.decrement();
      out.write('}');
      out.close();
      return writer.toString();
   }

   public void toJSON(Object value, IndentPrinter out) throws IOException
   {
      if (value.getClass().isAssignableFrom(Integer.class))
      {
         out.print(toJSON((int) value));
      } else if (value.getClass().isAssignableFrom(Long.class))
      {
         out.print(toJSON((long) value));
      } else if (value.getClass().isAssignableFrom(String.class))
      {
         out.print(toJSON((String) value, style));
      } else
      {
         out.print(serialize(value));
      }
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
