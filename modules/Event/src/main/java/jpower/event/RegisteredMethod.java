package jpower.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegisteredMethod
{
   private final Method method;

   public RegisteredMethod(Method method)
   {
      this.method = method;
   }

   public void invoke(Object instance, Object event)
   {
      try
      {
         method.invoke(instance, event);
      }
      catch (IllegalAccessException | InvocationTargetException ignored)
      {
      }
   }

   public Class<?> getEventType()
   {
      return method.getParameterTypes()[0];
   }
}
