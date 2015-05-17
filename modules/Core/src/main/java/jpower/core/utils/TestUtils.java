package jpower.core.utils;

import jpower.core.ActionThrowable;

public class TestUtils {
   public static Throwable thrown(ActionThrowable action) {
      try {
         action.run();
      } catch (Throwable throwable) {
         return throwable;
      }
      return null;
   }
}
