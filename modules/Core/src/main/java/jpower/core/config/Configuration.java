package jpower.core.config;

import jpower.core.ParseException;
import jpower.core.Wrapper;
import jpower.core.utils.FileUtils;
import jpower.core.utils.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class Configuration
{
   private static final Pattern KEY_VALUE_SPLIT = Pattern.compile("=");

   private final List<Property> props = new ArrayList<>();
   private final File file;

   private boolean lock;

   public Configuration(File file)
   {
       this.file = file;
   }

   public void lock()
   {
      lock = true;
   }

   public void unlock()
   {
      lock = false;
   }

   public void load() throws IOException
   {
      if (!file.exists())
      {
         throw new FileNotFoundException(file.getAbsolutePath() + " does not exist.");
      }
      else if (lock)
      {
         return;
      }
      props.clear();
      List<String> lines = FileUtils.readLines(file);
      processLines(lines);
   }

   private void processLines(Iterable<String> lines)
   {
      Wrapper<Integer> lineNumber = new Wrapper<>(0);
      final Collection<String> comments = new ArrayList<>();
      lines.forEach(line -> {
         line = line.trim();
         lineNumber.set(lineNumber.get() + 1);
         if (line.startsWith("#"))
         {
            comments.add(line.substring(1).trim());
         }
         else
         {
            String[] parts = KEY_VALUE_SPLIT.split(line, 2); /* Splits at the first index of '=' */
            if (parts.length != 2)
            {
               throw new ParseException(lineNumber.get(), "Invalid Configuration Entry: " + line);
            }
            String value = parts[1];
            Property property = new Property(parts[0]);
            property.comments().addAll(comments);
            comments.clear();
            property.set(value);
            props.add(property);
         }
      });
   }

   private List<String> toList()
   {
      List<String> lines = new ArrayList<>();
      props.forEach(property -> lines.addAll(property.toLines()));
      return lines;
   }

   public void save() throws IOException
   {
      if (lock)
      {
         return;
      }
      file.createNewFile();
      FileUtils.write(file, toString());
   }

   public List<Property> properties()
   {
      return props;
   }

   public Property getProperty(String key)
   {
      for (Property prop : props)
      {
         if (prop.key().equals(key))
         {
            return prop;
         }
      }
      return null;
   }

   public String get(String key)
   {
      Property property = getProperty(key);
      if (property == null)
      {
         return null;
      }
      return property.value();
   }

   public Property set(String key, String value)
   {
      Property property = getProperty(key);
      if (property == null)
      {
         property = new Property(key);
         props.add(property);
      }
      property.set(value);
      return property;
   }

   public List<Property> group(String groupName)
   {
      List<Property> group = new ArrayList<>();
      props.forEach(property -> {
         if (property.key().startsWith(groupName + '.'))
         {
            group.add(property);
         }
      });
      return group;
   }

   public Properties toProperties()
   {
      Properties properties = new Properties();
      props.forEach(property -> properties.setProperty(property.key(), property.value()));
      return properties;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      properties().forEach(property -> builder.append(property.toString()));
      return builder.toString();
   }
}
