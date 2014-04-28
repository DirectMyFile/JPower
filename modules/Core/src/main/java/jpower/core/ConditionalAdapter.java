package jpower.core;

import java.util.function.Predicate;

public class ConditionalAdapter<T>
{
   private final T value;

   public ConditionalAdapter(T value)
   {
      this.value = value;
   }

   public ConditionalAdapter<T> when(T left, Runnable action)
   {
      if (value == left || (value != null && value.equals(left)))
      {
         action.run();
      }
      return this;
   }

   public ConditionalAdapter<T> when(Predicate<T> filter, Runnable action)
   {
      if (filter.test(value))
      {
         action.run();
      }
      return this;
   }

   public ConditionalAdapter<T> not(T left, Runnable action)
   {
      if (!(value == left || (value != null && value.equals(left))))
      {
         action.run();
      }
      return this;
   }

   public ConditionalAdapter<T> not(Predicate<T> filter, Runnable action)
   {
      if (filter.negate().test(value))
      {
         action.run();
      }
      return this;
   }

   public static <T> ConditionalAdapter<T> of(T value)
   {
      return new ConditionalAdapter<>(value);
   }
}
