package jpower.gcp.test;

import jpower.gcp.GCP;
import jpower.gcp.GCPLine;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertFalse;

public class LineTest
{

   private final Collection<String> inputs = new ArrayList<>();

   @Before
   public void prepare()
   {
      inputs.add("MSG\u0001User\u0002TestUser\u0001Message\u0002Hello World");
   }

   @Test
   public void testLineExamples()
   {
      inputs.forEach(input -> {
         GCPLine line = GCP.parse(input);
         assertFalse(line.opts().isEmpty());
      });
   }
}
