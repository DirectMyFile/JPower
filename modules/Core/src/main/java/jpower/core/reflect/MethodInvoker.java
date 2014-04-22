package jpower.core.reflect;

import jpower.core.utils.ThreadUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Invokes Methods in Certain Fashions
 */
public class MethodInvoker
{
   private final Object object;
   private final Class<?> clazz;

   public MethodInvoker(Object object)
   {
      this.object = object;
      clazz = object.getClass();
   }

   public MethodInvoker(Class<?> clazz)
   {
      this.clazz = clazz;
      object = null;
   }

   public Object invokeMethod(String name,
                              Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
   {
      Class<?>[] paramTypes = new Class<?>[args.length];
      for (int i = 0; i < args.length; i++)
      {
         paramTypes[i] = args[i].getClass();
      }
      Method method = clazz.getDeclaredMethod(name, paramTypes);
      if (!method.isAccessible())
      {
         method.setAccessible(true);
      }
      return method.invoke(object, args);
   }

   public void invokeAsync(InvokeListener listener, String name, Object... args)
   {
      ThreadUtils.startDaemon(() -> {
         listener.before();
         try
         {
            listener.after(invokeMethod(name, args));
         }
         catch (Exception e)
         {
            listener.error(e);
         }
      });
   }

   public void invokeAsync(String name, Object... args)
   {
      invokeAsync(InvokeListener.ignore(), name, args);
   }
}