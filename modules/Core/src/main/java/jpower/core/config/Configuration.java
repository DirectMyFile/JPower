package jpower.core.config;

import jpower.core.ParseException;
import jpower.core.Wrapper;
import jpower.core.utils.FileUtils;
import jpower.core.utils.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class Configuration
{
   private static final Pattern KEY_VALUE_SPLIT = Pattern.compile(":");
   private final List<Property> props;

   public Configuration()
   {
      props = new ArrayList<>();
   }

   public void load(File file) throws IOException
   {
      if (!file.exists())
      {
         return;
      }
      List<String> lines = Files.readAllLines(file.toPath());
      processLines(lines);
   }

   public void load(Reader input) throws IOException
   {
      reset();
      BufferedReader reader = new BufferedReader(input);
      processLines(IOUtils.readLines(reader));
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
            comments.add(line);
         } else
         {
            String[] parts = KEY_VALUE_SPLIT.split(line, 2); /* Splits at the first index of ':' */
            if (parts.length != 2)
            {
               throw new ParseException(lineNumber.get(), "Invalid Configuration Entry: " + line);
            }
            String key = parts[0];
            if (parts[1].charAt(0) != ' ')
            {
               throw new ParseException(lineNumber.get(), line.indexOf('A') + 1, "A space is required after the ':' and before the value");
            }
            String value = parts[1].substring(1);
            Property property = new Property(key);
            property.comments().addAll(comments);
            comments.clear();
            property.set(value);
            props.add(property);
         }
      });
   }

   private List<String> generate()
   {
      List<String> lines = new ArrayList<>();
      props.forEach(property -> {
         lines.addAll(property.toLines());
      });
      return lines;
   }

   public void save(File file) throws IOException
   {
      if (file.exists())
      {
         //noinspection ResultOfMethodCallIgnored
         file.delete();
      }
      FileUtils.write(file, generate());
   }

   public void save(PrintStream stream)
   {
      generate().forEach(stream::println);
   }

   public void reset()
   {
      props.clear();
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
      props.forEach(property -> {
         properties.setProperty(property.key(), property.value());
      });
      return properties;
   }

}
