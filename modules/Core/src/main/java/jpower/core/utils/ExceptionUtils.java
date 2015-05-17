package jpower.core.utils;

public class ExceptionUtils {
   public static void throwUnchecked(Exception e) {
      throw new RuntimeException(e);
   }
}
