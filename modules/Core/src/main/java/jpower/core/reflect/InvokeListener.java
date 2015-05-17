package jpower.core.reflect;

import java.util.function.Consumer;

@SuppressWarnings("EmptyMethod")
public interface InvokeListener {
   static InvokeListener none() {
      return new InvokeListener() {
         @Override
         public void before() {

         }

         @Override
         public void after(Object returned) {

         }

         @Override
         public void error(Exception e) {

         }
      };
   }

   static InvokeListener handle(Consumer<Object> handler) {
      return new InvokeListener() {
         @Override
         public void before() {

         }

         @Override
         public void after(Object returned) {
            handler.accept(returned);
         }

         @Override
         public void error(Exception e) {

         }
      };
   }

   void before();

   void after(Object returned);

   void error(Exception e);
}
