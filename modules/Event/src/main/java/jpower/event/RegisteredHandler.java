package jpower.event;

import jpower.core.Wrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A Registered Event Handler
 */
public class RegisteredHandler
{
   private final Object object;
   private final Collection<RegisteredMethod> methods = new ArrayList<>();
   private Class<? extends Annotation> annotationType;

   /**
    * Creates a new Registered Handler
    *
    * @param object handler object
    */
   public RegisteredHandler(Object object)
   {
      this.object = object;
   }

   /**
    * Sets the Annotation to look for
    * @param annotationType annotation type
    * @return this
    */
   public RegisteredHandler setAnnotationType(Class<? extends Annotation> annotationType)
   {
      this.annotationType = annotationType;
      return this;
   }

   /**
    * Registers all Handler Methods
    * @return this
    */
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

   /**
    * Checks if the Specified Method is an Event Handler
    * @param method method to check
    * @return true if it is an event handler, otherwise false
    */
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

   /**
    * Gets the Handler Object
    * @return handler object
    */
   public Object getObject()
   {
      return object;
   }
}
