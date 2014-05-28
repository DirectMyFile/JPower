package jpower.core;

import jpower.core.reflect.FieldAccessor;

import java.util.ArrayList;
import java.util.List;

public class HashCodeBuilder
{
   private final List<String> includes = new ArrayList<>();

   public static HashCodeBuilder create()
   {
      return new HashCodeBuilder();
   }

   public HashCodeBuilder include(String field)
   {
      includes.add(field);
      return this;
   }

   public int hashCodeFor(Object obj)
   {
      FieldAccessor accessor = new FieldAccessor(obj);
      int code = 0;
      for (String fieldName : includes)
      {
         try
         {
            Object val = accessor.get(fieldName);
            if (val != null)
            {
               code += 31 * val.hashCode();
            }
         }
         catch (NoSuchFieldException | IllegalAccessException e)
         {
            throw new RuntimeException(e);
         }
      }
      return code;
   }
}
