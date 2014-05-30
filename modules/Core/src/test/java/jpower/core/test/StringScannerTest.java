package jpower.core.test;

import jpower.core.utils.RegularExpression;
import jpower.core.utils.StringScanner;
import org.intellij.lang.annotations.Language;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Ignore
public class StringScannerTest
{
   @Test
   public void testHelloWorldScan()
   {
      @Language("RegExp")
      String pattern = "(H|h)ello (W|w)orld";

      StringScanner scanner = RegularExpression.scanner("Hello World");

      String output = scanner.scan(pattern);
      assertNotNull(output);
      assertEquals("Hello World", output);
   }

   @Test
   public void testSkipping()
   {
      @Language("RegExp")
      String skip = "\\|SKIPIT\\|";

      StringScanner scanner = RegularExpression.scanner("Hello|SKIPIT|World");
      assertEquals("Hello", scanner.scan("Hello"));
      scanner.skip(skip);
      assertEquals("World", scanner.scan("World"));
   }
}
