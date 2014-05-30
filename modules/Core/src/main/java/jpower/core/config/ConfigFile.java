package jpower.core.config;

import jpower.core.load.Loadable;
import jpower.core.load.Reloadable;
import jpower.core.utils.ExceptionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigFile implements Loadable, Reloadable
{
   private final Path path;
   private final Configuration configuration = new Configuration();

   public ConfigFile(Path path)
   {
      this.path = path;
   }

   public ConfigFile(File file)
   {
      this.path = file.toPath();
   }

   @Override
   public void load()
   {
      try
      {
         configuration.load(path.toFile());
      }
      catch (IOException e)
      {
         ExceptionUtils.throwUnchecked(e);
      }
   }

   @Override
   public void reload()
   {
      configuration.reset();
      load();
   }

   public Configuration getConfiguration()
   {
      return configuration;
   }
}
