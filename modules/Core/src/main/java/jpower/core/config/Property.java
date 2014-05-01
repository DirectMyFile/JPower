package jpower.core.config;

import java.util.ArrayList;
import java.util.List;

public class Property
{
   private final List<String> comments = new ArrayList<>();
   private final String key;
   private String value;

   public Property(String key)
   {
      this.key = key;
   }

   public void set(String value)
   {
      this.value = value;
   }

   public Property addComment(String comment)
   {
      comments.add(comment);
      return this;
   }

   public List<String> comments()
   {
      return comments;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();

      comments.forEach(comment -> builder.append('#').append(' ').append(comment).append('\n'));
      builder.append(key).append(':').append(' ').append(value).append('\n');

      return builder.toString();
   }

   public List<String> toLines()
   {
      List<String> lines = new ArrayList<>();
      comments.forEach(comment -> lines.add("# " + comment));
      lines.add(key + ": " + value);
      return lines;
   }

   public String key()
   {
      return key;
   }

   public String get()
   {
      return value;
   }

   public String value()
   {
      return value;
   }
}
