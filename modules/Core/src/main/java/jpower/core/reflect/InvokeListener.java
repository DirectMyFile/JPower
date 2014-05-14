package jpower.core.reflect;

public interface InvokeListener
{
   void before();

   void after(Object returned);

   void error(Exception e);

   static InvokeListener none()
   {
      return new InvokeListener()
      {
         @Override
         public void before()
         {

         }

         @Override
         public void after(Object returned)
         {

         }

         @Override
         public void error(Exception e)
         {

         }
      };
   }
   
   static InvokeListener handle(Consumer<Object> handler) {
      return new InvokeListener()
      {
         @Override
         public void before()
         {

         }

         @Override
         public void after(Object returned)
         {
            handler.accept(returned);
         }

         @Override
         public void error(Exception e)
         {

         }
      };
   }
}
