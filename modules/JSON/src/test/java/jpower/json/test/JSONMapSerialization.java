package jpower.json.test;

import jpower.json.JSON;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JSONMapSerialization
{
   private JSON json;

   @Before
   public void prepare()
   {
      json = JSON.create();
   }

   @Test
   public void testBasicMap() throws IOException
   {
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("message", "Hello World");
      String output = json.serialize(map);
      assertEquals("{\n    \"message\": \"Hello World\"\n}", output);
   }

   @Test
   public void testAdvancedMap() throws IOException
   {
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("messageA", "Hello World");
      map.put("messageB", "LOL");
      map.put("numberA", 1);
      String output = json.serialize(map);
      assertEquals("{\n    \"messageA\": \"Hello World\",\n    \"messageB\": \"LOL\",\n    \"numberA\": 1\n}", output);
   }
}
