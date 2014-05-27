package jpower.core.test;

import jpower.core.Wrapper;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("EqualsBetweenInconvertibleTypes")
public class WrapperTest
{
   @Test
   public void testGet()
   {
      Wrapper<String> hello = new Wrapper<>("Hello");
      assertEquals("Hello", hello.get());
   }

   @Test
   public void testSet()
   {
      Wrapper<String> hello = new Wrapper<>("Hello");
      assertEquals("Hello", hello.get());
      String old = hello.set("New Value");
      assertEquals("New Value", hello.get());
      assertEquals("Hello", old);
   }

   @Test
   public void testIsNull()
   {
      Wrapper<String> obj = new Wrapper<>(null);
      assertTrue(obj.isNull());
   }

   @Test
   public void testIsNotNull()
   {
      Wrapper<String> obj = new Wrapper<>("Hello World");
      assertFalse(obj.isNull());
      assertTrue(obj.isNotNull());
   }

   @Test
   public void testEquals()
   {
      Wrapper<String> obj = new Wrapper<>("Hello World");
      assertTrue(obj.equals("Hello World"));
   }

   @Test
   public void testEqualsNull()
   {
      Wrapper<String> obj = new Wrapper<>(null);
      assertTrue(obj.equals(null));
      assertFalse(obj.equals("Hello"));
   }

   @Test
   public void testHashCode()
   {
      Wrapper<String> obj = new Wrapper<>("Hello World");
      assertEquals("Hello World".hashCode(), obj.hashCode());
   }

   @Test
   public void testHashCodeNull()
   {
      Wrapper<String> obj = new Wrapper<>(null);
      assertEquals(0, obj.hashCode());
   }

   @Test
   public void testOf()
   {
      assertEquals(Wrapper.of(null).hashCode(), new Wrapper<>(null).hashCode());
   }
}
