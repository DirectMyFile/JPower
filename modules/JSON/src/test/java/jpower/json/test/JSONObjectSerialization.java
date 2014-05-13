package jpower.json.test;

import jpower.json.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSONObjectSerialization
{
   @Test
   public void testGeneralObject()
   {
      JSON json = JSON.create();
      String output = json.serialize(new TestObject());
      assertEquals("{\"message\":\"Test\"}", output);
   }

   @Test
   public void testCustomKey()
   {
      JSON json = JSON.create();
      String output = json.serialize(new TestObjectCustom());
      assertEquals("{\"message\":\"Hello World\"}", output);
   }
}
