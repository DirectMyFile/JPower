package jpower.core.utils;

import jpower.core.ActionThrowable;

public class CallUtils
{
   public static void callIgnoreExceptions(ActionThrowable action)
   {
      try
      {
         action.run();
      } catch (Throwable ignored)
      {
      }
   }
}
