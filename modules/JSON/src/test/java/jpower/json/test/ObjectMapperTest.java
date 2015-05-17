package jpower.json.test;

import jpower.json.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ObjectMapperTest {

   private ObjectMapper mapper;

   @Before
   public void before() {
      this.mapper = new ObjectMapper();
   }

   @Test
   public void testMappingSimpleObject() {
      TestObject object = new TestObject();

      Map<String, Object> expected = new HashMap<String, Object>() {{
         put("message", "Test");
      }};

      Map<String, Object> actual = mapper.create(object);

      assertEquals(expected, actual);
   }
}
