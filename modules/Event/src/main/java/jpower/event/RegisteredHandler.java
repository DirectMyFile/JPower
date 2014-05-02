package jpower.event;

import jpower.core.Wrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public class RegisteredHandler
{
   private final Object object;
   private final Collection<RegisteredMethod> methods = new ArrayList<>();
   private Class<? extends Annotation> annotationType;

   public RegisteredHandler(Object object)
   {
      this.object = object;
   }

   public RegisteredHandler setAnnotationType(Class<? extends Annotation> annotationType)
   {
      this.annotationType = annotationType;
      return this;
   }

   public RegisteredHandler registerMethods()
   {
      for (Method method : object.getClass().getMethods())
      {
         if (isEventMethod(method))
         {
            methods.add(new RegisteredMethod(method));
         }
      }
      return this;
   }

   private boolean isEventMethod(Method method)
   {
      return method.isAnnotationPresent(annotationType) && method.getParameterTypes().length == 1;
   }

   public boolean executeEvent(Object event)
   {
      Wrapper<Boolean> executed = new Wrapper<>(false);
      methods.forEach(method -> {
         if (method.getEventType().isAssignableFrom(event.getClass()))
         {
            method.invoke(object, event);
            executed.set(true);
         }
      });
      return executed.get();
   }

   public Object getObject()
   {
      return object;
   }
}
