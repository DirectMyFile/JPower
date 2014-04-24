package jpower.core.utils;

import jpower.core.ActionThrowable;

import java.util.function.Consumer;

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

   public static void callPrintStackTrace(ActionThrowable action)
   {
      try
      {
         action.run();
      } catch (Throwable e)
      {
         e.printStackTrace();
      }
   }

   public static void tryCatch(ActionThrowable action, Consumer<Throwable> exceptionHandler)
   {
      try
      {
         action.run();
      } catch (Throwable e)
      {
         exceptionHandler.accept(e);
      }
   }
}
