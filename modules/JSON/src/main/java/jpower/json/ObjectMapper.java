package jpower.json;

import jpower.json.serialization.JSONKey;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectMapper {
   public Map<String, Object> create(Object obj) {
      Map<String, Object> map = new LinkedHashMap<>();
      for (Field field : obj.getClass().getDeclaredFields()) {
         try {
            if (!field.isAccessible()) {
               field.setAccessible(true);
            }

            String name = field.getName();

            if (field.isAnnotationPresent(JSONKey.class)) {
               name = field.getAnnotation(JSONKey.class).value();
            }

            map.put(name, field.get(obj));
         } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
         }
      }
      return map;
   }
}
