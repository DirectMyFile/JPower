package jpower.json.test;

import jpower.json.JSON;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSONObjectSerialization
{
   private JSON json;

   @Before
   public void prepare()
   {
      json = JSON.create();
   }

   @Test
   public void testGeneralObject()
   {
      String output = json.serialize(new TestObject());
      assertEquals("{\"message\":\"Test\"}", output);
   }

   @Test
   public void testCustomKey()
   {
      String output = json.serialize(new TestObjectCustom());
      assertEquals("{\"message\":\"Hello World\"}", output);
   }

   @Test
   public void testContainingObject()
   {
      String output = json.serialize(new ContainingObject());
      assertEquals("{\"test\":{\"message\":\"Test\"},\"custom\":{\"message\":\"Hello World\"}}", output);
   }
}
