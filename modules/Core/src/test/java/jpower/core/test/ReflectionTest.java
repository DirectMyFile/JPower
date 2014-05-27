package jpower.core.test;

import org.junit.Test;
import static org.junit.Assert.*;

import jpower.core.reflect.*;

public class ReflectionTest
{
   public static String FIELD = "Hello World";

   public String INNER_FIELD = "Inner Hello World";

   public static final String FINAL_FIELD = "Hello World";

   private final String FINAL_INNER_FIELD = "Hello World";

   @Test
   public void testFieldAccessStatic() throws Exception
   {
      FieldAccessor accessor = new FieldAccessor(ReflectionTest.class);
      Object field = accessor.get("FIELD");
      assertNotNull(field);
      assertTrue(field instanceof String);
      assertEquals("Hello World", field);
   }

   @Test
   public void testFieldAccessInner() throws Exception
   {
      FieldAccessor accessor = new FieldAccessor(this);
      Object field = accessor.get("INNER_FIELD");
      assertNotNull(field);
      assertTrue(field instanceof String);
      assertEquals("Inner Hello World", field);
   }

   @Test
   public void testFieldAccessSetInner() throws Exception
   {
      FieldAccessor accessor = new FieldAccessor(this);
      accessor.set("INNER_FIELD", "New Value");
      assertEquals("New Value", INNER_FIELD);
   }

   @Test
   public void testFieldAccessSetStatic() throws Exception
   {
      FieldAccessor accessor = new FieldAccessor(ReflectionTest.class);
      accessor.set("FIELD", "New Value");
      assertEquals("New Value", FIELD);
      FIELD = "Hello World";
   }

   @Test(expected = Exception.class)
   public void testFieldSetFinalStatic() throws Exception
   {
      FieldAccessor accessor = new FieldAccessor(ReflectionTest.class);
      accessor.set("FINAL_FIELD", "Test");
      assertEquals("Hello World", FINAL_FIELD);
   }

   @Test
   public void testFieldSetFinal() throws Exception
   {
      FieldAccessor accessor = new FieldAccessor(this);
      accessor.set("FINAL_INNER_FIELD", "Test");
      // assertEquals("Hello World", FINAL_INNER_FIELD);
   }
}
