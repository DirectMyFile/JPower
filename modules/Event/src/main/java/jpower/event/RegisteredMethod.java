package jpower.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A Method that is Registered as an Event Handler
 */
public class RegisteredMethod
{
   private final Method method;

   /**
    * Creates a new Registered Method
    *
    * @param method method that is registered
    */
   public RegisteredMethod(Method method)
   {
      this.method = method;
   }

   /**
    * Invokes the Registered Method
    * @param instance instance to invoke on
    * @param event event to invoke for
    */
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

   /**
    * The Event Type
    * @return Event Type
    */
   public Class<?> getEventType()
   {
      return method.getParameterTypes()[0];
   }
}
