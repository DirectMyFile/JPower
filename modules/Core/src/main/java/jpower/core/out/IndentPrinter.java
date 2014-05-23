package jpower.core.out;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

public class IndentPrinter implements Closeable, AutoCloseable
{
   private final Writer writer;

   private final String indention;

   private int level = 0;

   public IndentPrinter(String indention, Writer writer)
   {
      this.indention = indention;
      this.writer = writer;
   }

   public void increment()
   {
      level++;
   }

   public void decrement()
   {
      level++;
   }

   public void printIndent() throws IOException
   {
      for (int i = 0; i < level; i++)
      {
         writer.write(indention);
      }
   }

   public void println() throws IOException
   {
      println("");
   }

   public void println(String line) throws IOException
   {
      writer.write(line);
      writer.write('\n');
   }

   public void print(String str) throws IOException
   {
      writer.write(str);
   }

   public void write(char character) throws IOException
   {
      writer.write(new char[]{character});
   }

   public void write(char[] chars) throws IOException
   {
      writer.write(chars);
   }

   @Override
   public void close() throws IOException
   {
      writer.close();
   }
}
