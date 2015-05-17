package jpower.core;

import jpower.core.utils.ListUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("UnusedDeclaration")
public class GenericSniffer<T> {
   public Type[] sniff() {
      Type superclass = getClass().getGenericSuperclass();
      return ((ParameterizedType) superclass).getActualTypeArguments();
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append('[').append(' ');
      List<String> names = new ArrayList<>();
      Stream.of(sniff()).forEach(type -> names.add(type.getTypeName()));
      builder.append(ListUtils.join(names, ", "));
      builder.append(' ').append(']');
      return builder.toString();
   }
}
