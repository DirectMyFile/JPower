package jpower.core.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IOUtils
{
   /**
    * Retrieves a Resource as a String if exists, else returns null
    *
    * @param clazz    class to get resource for
    * @param resource resource name
    * @return resource as string
    */
   public static String getResourceAsString(Class<?> clazz, String resource)
   {
      InputStream stream = clazz.getResourceAsStream(resource);
      if (stream == null)
      {
         return null;
      }
      return toString(stream);
   }

   public static String toString(InputStream stream)
   {
      BufferedReader reader = createBufferedReader(stream);
      StringWriter writer = new StringWriter();
      int b;
      try
      {
         while ((b = reader.read()) != -1)
         {
            writer.write(b);
         }
         stream.close();
      }
      catch (IOException e)
      {
         return null;
      }
      return writer.toString();
   }

   public static byte[] getBytes(InputStream stream) throws IOException
   {
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      byte b;
      while ((b = (byte) stream.read()) != -1)
      {
         buffer.write(b);
      }
      return buffer.toByteArray();
   }

   public static BufferedReader createBufferedReader(InputStream stream)
   {
      return createBufferedReader(createReader(stream));
   }

   public static BufferedReader createBufferedReader(Reader reader)
   {
      return new BufferedReader(reader);
   }

   public static Reader createReader(InputStream stream)
   {
      return new InputStreamReader(stream);
   }

   public static PrintStream createPrintStream(OutputStream stream)
   {
      return new PrintStream(stream);
   }

   public static void eachLine(BufferedReader reader, Consumer<String> handler) throws IOException
   {
      String line;
      while ((line = reader.readLine()) != null)
      {
         handler.accept(line);
      }
   }

   public static List<String> readLines(BufferedReader reader) throws IOException
   {
      List<String> lines = new ArrayList<>();
      eachLine(reader, lines::add);
      return lines;
   }

   public static List<String> readLines(InputStream stream) throws IOException
   {
      return readLines(createBufferedReader(stream));
   }

   public static void eachLine(InputStream stream, Consumer<String> handler) throws IOException
   {
      eachLine(createBufferedReader(stream), handler);
   }

   public static void splitEachLine(BufferedReader reader, String by, Consumer<String[]> handler) throws IOException
   {
      eachLine(reader, line -> handler.accept(line.split(by)));
   }
}
