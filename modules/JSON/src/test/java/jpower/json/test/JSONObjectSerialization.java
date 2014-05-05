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
      assertEquals("{\n    \"message\": \"Test\"\n}", output);
   }
}
