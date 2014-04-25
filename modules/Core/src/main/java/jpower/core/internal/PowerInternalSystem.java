package jpower.core.internal;

import jpower.core.reflect.FieldAccessor;
import sun.misc.Unsafe;

import java.lang.instrument.Instrumentation;
import java.util.Vector;

public class PowerInternalSystem
{
   private static Instrumentation inst;

   public static void premain(String args, Instrumentation inst)
   {
      PowerInternalSystem.inst = inst;
   }

   public static void agentmain(String args, Instrumentation inst)
   {
      PowerInternalSystem.inst = inst;
   }

   @SuppressWarnings({"unchecked", "UseOfObsoleteCollectionType"})
   public static Class[] getLoadedClasses(ClassLoader loader)
   {
      try
      {
         Vector<Class> classList = (Vector<Class>) new FieldAccessor(ClassLoader.class, loader).get("classes");
         return classList.toArray(new Class[classList.size()]);
      } catch (NoSuchFieldException | IllegalAccessException e)
      {
         return null;
      }
   }

   public static Instrumentation getInstrumentation()
   {
      return inst;
   }

   public static Unsafe getUnsafe()
   {
      try
      {
         return (Unsafe) new FieldAccessor(Unsafe.class).get("theUnsafe");
      } catch (Throwable e)
      {
         return null;
      }
   }

   public static boolean hasInstrumentation()
   {
      return getInstrumentation() != null;
   }
}
