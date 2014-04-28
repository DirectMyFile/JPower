package jpower.core.utils;

import jpower.core.Wrapper;

import java.util.*;
import java.util.stream.Stream;

public class ArrayUtils
{
   public static boolean containsDuplicates(Object[] objects)
   {
      Collection<Object> list = new ArrayList<>();
      Wrapper<Boolean> duplicates = new Wrapper<>(false);
      Stream.of(objects).forEach(obj -> {
         if (list.contains(obj))
         {
            duplicates.set(true);
         } else
         {
            list.add(obj);
         }
      });
      return duplicates.get();
   }

   public static int count(Object obj, Object[] in)
   {
      Wrapper<Integer> count = new Wrapper<>(0);
      Stream.of(in).forEach(item -> {
         if (item == obj)
         {
            count.set(count.get() + 1);
         }
      });
      return count.get();
   }

   public static <T> List<T> toList(T[] array)
   {
      List<T> list = new ArrayList<>();
      Stream.of(array).forEach(list::add);
      return list;
   }

   public static <T> Set<T> toSet(T[] array)
   {
      Set<T> set = new HashSet<>();
      Stream.of(array).forEach(e -> {
         if (!set.contains(e))
         {
            set.add(e);
         }
      });
      return set;
   }

   @SuppressWarnings("unchecked")
   public static <T> T[] reverse(T[] original)
   {
      T[] reversed = original.clone();
      int count = 0;
      for (int i = original.length - 1; i >= 0; i--)
      {
         reversed[i] = original[count];
         count++;
      }
      return reversed;
   }
}
