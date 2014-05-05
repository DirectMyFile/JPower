package jpower.json.test;

import jpower.json.serialization.JSONSerializer;
import jpower.json.serialization.JSONStyle;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JSONMapSerialization
{
   @Test
   public void testBasicMap() throws IOException
   {
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("message", "Hello World");
      String output = new JSONSerializer(JSONStyle.defaultStyle()).serialize(map);
      assertEquals("{\n    \"message\": \"Hello World\"\n}", output);
   }

   @Test
   public void testAdvancedMap() throws IOException
   {
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("messageA", "Hello World");
      map.put("messageB", "LOL");
      map.put("numberA", 1);
      String output = new JSONSerializer(JSONStyle.defaultStyle()).serialize(map);
      assertEquals(output, "{\n    \"messageA\": \"Hello World\",\n    \"messageB\": \"LOL\",\n    \"numberA\": 1\n}");
   }
}
