package jpower.core.convert;

public interface Convertible
{
   <T> T convert(Class<T> clazz);
}
