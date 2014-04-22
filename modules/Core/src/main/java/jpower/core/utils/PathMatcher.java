package jpower.core.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

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

   public List<Path> find() throws IOException
   {
      List<Path> paths = new LinkedList<>();
      Files.walk(parent).forEach(path -> {
         if (matcher.test(path))
         {
            paths.add(path);
         }
      });
      return paths;
   }
}
