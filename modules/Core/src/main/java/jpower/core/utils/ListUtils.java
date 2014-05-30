package jpower.core.utils;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Common List Utilities
 */
public class ListUtils
{
   public static <T> List<T> singleton(T single)
   {
      return Collections.singletonList(single);
   }

   /**
    * Joins each String by the line separator
    *
    * @param lines input strings
    * @return string of each string joined by @code {System.lineSeparator()}
    */
   public static String toString(List<String> lines)
   {
      return join(lines, System.lineSeparator());
   }

   /**
    * Joins a list of strings by the specified string
    * @param inputs input strings
    * @param joinBy joined by
    * @return string joined by the specified string
    */
   public static String join(List<String> inputs, String joinBy)
   {
      StringBuilder builder = new StringBuilder();
      forEach(inputs, (input, i) -> {
         if (i != 0)
         {
            builder.append(joinBy);
         }
         builder.append(input);
      });
      return builder.toString();
   }

   @SuppressWarnings("unchecked")
   public static <T> boolean equals(@NotNull T[] array, @NotNull List<T> list)
   {
      T[] listArray = (T[]) list.toArray();
      return Arrays.equals(array, listArray);
   }

   public static <T> void forEach(List<T> that, BiConsumer<T, Integer> consumer)
   {
      for (int i = 0; i < that.size(); i++)
      {
         consumer.accept(that.get(i), i);
      }
   }

   public static <T> Set<T> toSet(List<T> list)
   {
      return Collections.unmodifiableSet(new HashSet<>(list));
   }

   public static <T, R> List<R> collect(List<T> input, Function<T, R> function)
   {
      List<R> newStuff = new ArrayList<>();
      input.forEach(entry -> newStuff.add(function.apply(entry)));
      return newStuff;
   }
}
