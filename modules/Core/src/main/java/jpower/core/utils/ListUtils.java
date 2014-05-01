package jpower.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ListUtils
{
   public static <T> List<T> singleton(T single)
   {
      return Collections.singletonList(single);
   }

   public static String toString(List<String> lines)
   {
      return join(lines, System.lineSeparator());
   }

   public static String join(List<String> inputs, String joinBy)
   {
      StringBuilder builder = new StringBuilder();
      forEach(inputs, (input, i) -> {
         if (i != 0 && i != inputs.size() - 1)
         {
            builder.append(joinBy);
         }
         builder.append(input);
      });
      return builder.toString();
   }

   public static <T> void forEach(List<T> that, BiConsumer<T, Integer> consumer)
   {
      for (int i = 0; i < that.size(); i++)
      {
         consumer.accept(that.get(i), i);
      }
   }

   public static <T, R> List<R> collect(List<T> input, Function<T, R> function)
   {
      List<R> newStuff = new ArrayList<>();
      input.forEach(entry -> newStuff.add(function.apply(entry)));
      return newStuff;
   }
}
