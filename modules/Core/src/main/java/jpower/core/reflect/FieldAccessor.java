package jpower.core.reflect;

import java.lang.reflect.Field;

public class FieldAccessor
{
   private final Object object;
   private final Class<?> clazz;
   private final boolean declared;

   public FieldAccessor(Class<?> clazz)
   {
      this(clazz, null);
   }

   public FieldAccessor(Object object)
   {
      this(object.getClass(), object);
   }

   public FieldAccessor(Class<?> clazz, Object object)
   {
      this(clazz, object, true);
   }

   public FieldAccessor(Class<?> clazz, Object object, boolean declared) {
      this.clazz = clazz;
      this.object = object;
      this.declared = declared;
   }

   public Field field(String name) throws NoSuchFieldException
   {
      Field field = declared ? clazz.getDeclaredField(name) : clazz.getField(name);
      if (!field.isAccessible())
      {
         field.setAccessible(true);
      }
      return field;
   }

   public Object get(String fieldName) throws NoSuchFieldException, IllegalAccessException
   {
      return field(fieldName).get(object);
   }

   public void set(String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException
   {
      field(fieldName).set(object, value);
   }
}
