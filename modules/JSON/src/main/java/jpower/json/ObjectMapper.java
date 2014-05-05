package jpower.json;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectMapper
{
   public static Map<String, Object> create(Object obj) {
      Map<String, Object> map = new LinkedHashMap<>();
      for (Field field : obj.getClass().getDeclaredFields()) {
         try
         {
            if (!field.isAccessible())
            {
               field.setAccessible(true);
            }
            map.put(field.getName(), field.get(obj));
         } catch (IllegalAccessException e)
         {
            throw new RuntimeException(e);
         }
      }
      return map;
   }
}
