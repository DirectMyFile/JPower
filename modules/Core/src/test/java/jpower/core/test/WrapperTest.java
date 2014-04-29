package jpower.core.test;

import jpower.core.Wrapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("EqualsBetweenInconvertibleTypes")
public class WrapperTest
{
   @Test
   public void test() {
      Wrapper<String> hello = new Wrapper<>("Hello");
      assertEquals("Hello", hello.get());
      assertEquals("Hello".hashCode(), hello.hashCode());
      assertTrue(hello.equals("Hello"));
   }
}
