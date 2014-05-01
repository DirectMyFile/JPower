package jpower.core.convert;

public class ConvertUtils
{
   public static <T> T convert(Convertible object, Class<T> clazz)
   {
      return object.convert(clazz);
   }
}
