package jpower.gcp.test;

import jpower.gcp.GCP;
import jpower.gcp.GCPLine;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class LineTest
{

   private final Collection<LineExample> inputs = new ArrayList<>();

   @Before
   public void prepare()
   {
      inputs.add(new LineExample("MSG\u0001User\u0002TestUser\u0001Message\u0002Hello World") {{
         command("MSG");
         opt("User", "TestUser");
         opt("Message", "Hello World");
      }});
   }

   @Test
   public void testLineExamples()
   {
      inputs.forEach(input -> {
         GCPLine line = GCP.parse(input.line);
         input.opts.keySet().forEach(key -> {
            assertTrue(line.opts().containsKey(key));
            assertEquals(input.command, line.command());
            assertEquals(input.opts.get(key), line.opt(key));
         });
      });
   }

   public static class LineExample {
      protected String line;
      protected String command;
      protected Map<String, String> opts = new HashMap<>();

      public LineExample(String line)
      {
         this.line = line;
      }

      public void opt(String key, String value)
      {
         opts.put(key, value);
      }

      public void command(String command)
      {
         this.command = command;
      }
   }
}
