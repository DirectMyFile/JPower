package jpower.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * A Visitor for Class Information
 */
public abstract class ClassVisitor {

   public final void visit(Class<?> clazz) {
      visitClass(clazz);

      visitPackage(clazz.getPackage());

      visitName(clazz.getName());

      visitSuperClass(clazz.getSuperclass());

      visitInterfaces(clazz.getInterfaces());

      for (Class<?> theInterface : clazz.getInterfaces()) {
         visitInterface(theInterface);
      }

      visitFields(clazz.getDeclaredFields());

      for (Field field : clazz.getDeclaredFields()) {
         visitField(field);
      }

      visitMethods(clazz.getDeclaredMethods());

      for (Method method : clazz.getDeclaredMethods()) {
         visitMethod(method);
      }
   }

   public void visitClass(Class<?> clazz) {
   }

   public void visitPackage(Package pkg) {
   }

   public void visitSuperClass(Class<?> superClass) {
   }

   public void visitInterface(Class<?> aInterface) {
   }

   public void visitInterfaces(Class<?>[] interfaces) {

   }

   public void visitName(String name) {
   }

   public void visitFields(Field[] fields) {
   }

   public void visitField(Field field) {
   }

   public void visitMethods(Method[] methods) {
   }

   public void visitMethod(Method method) {
   }
}
