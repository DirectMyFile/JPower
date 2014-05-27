package jpower.core.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PathMatcher
{
   private final Path parent;
   private final Predicate<Path> matcher;

   public PathMatcher(File directory, Predicate<Path> matcher)
   {
      this.matcher = matcher;
      this.parent = directory.toPath();
   }

   public PathMatcher(Path path, Predicate<Path> matcher)
   {
      this.matcher = matcher;
      this.parent = path;
   }

   public PathMatcher(Path path, Pattern pattern)
   {
      this.matcher = input -> {
         return pattern.matcher(input.toString()).matches();
      };
      this.parent = path;
   }

   public List<Path> find() throws IOException
   {
      List<Path> paths = new ArrayList<>();
      Files.walk(parent).forEach(path -> {
         if (matcher.test(path))
         {
            paths.add(path);
         }
      });
      return paths;
   }
}
