package jpower.core;

public class Wrapper<T>
{
   private T value;

   public Wrapper(T value)
   {
      this.value = value;
   }

   public T get()
   {
      return value;
   }

   public T set(T value)
   {
      T old = get();
      this.value = value;
      return old;
   }

   public boolean isNull()
   {
      return get() == null;
   }

   public boolean isNotNull()
   {
      return get() != null;
   }

   @Override
   public boolean equals(Object obj)
   {
      return value == obj || value != null && obj.getClass() == value.getClass() && value.equals(obj);
   }

   @Override
   public int hashCode()
   {
      return value == null ? 0 : value.hashCode();
   }

   public static <T> Wrapper<T> of(T value) {
      return new Wrapper<>(value);
   }
}
