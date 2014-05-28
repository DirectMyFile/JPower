package jpower.core.utils;

import jpower.core.ActionThrowable;
import jpower.core.reflect.MethodInvoker;

import java.util.function.Consumer;

public class CallUtils
{
   public static void callIgnoreExceptions(ActionThrowable action)
   {
      try
      {
         action.run();
      }
      catch (Throwable ignored)
      {
      }
   }

   public static void callAndRethrowUnchecked(ActionThrowable action)
   {
      try
      {
         action.run();
      }
      catch (Throwable e)
      {
         throw new RuntimeException(e);
      }
   }

   public static void callPrintStackTrace(ActionThrowable action)
   {
      try
      {
         action.run();
      }
      catch (Throwable e)
      {
         e.printStackTrace();
      }
   }

   public static void tryCatch(ActionThrowable action, Consumer<Throwable> exceptionHandler)
   {
      try
      {
         action.run();
      }
      catch (Throwable e)
      {
         exceptionHandler.accept(e);
      }
   }

   public static MethodInvoker invokerOf(Class<?> clazz)
   {
      return new MethodInvoker(clazz);
   }

   public static MethodInvoker invokerOf(Object object)
   {
      return new MethodInvoker(object);
   }
}
