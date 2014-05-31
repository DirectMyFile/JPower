package jpower.core.config;

import jpower.core.load.Loadable;
import jpower.core.load.Reloadable;
import jpower.core.utils.ExceptionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Configuration File Wrapper
 */
public class ConfigFile implements Loadable, Reloadable
{
   private final Path path;
   private final Configuration configuration = new Configuration();

   /**
    * Creates a ConfigFile from the path
    *
    * @param path path
    */
   public ConfigFile(Path path)
   {
      this.path = path;
   }

   /**
    * Creates a ConfigFile from the file
    * @param file file
    */
   public ConfigFile(File file)
   {
      this.path = file.toPath();
   }

   /**
    * Loads the Configuration File
    */
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

   /**
    * Reloads the Configuration File
    */
   @Override
   public void reload()
   {
      configuration.reset();
      load();
   }

   /**
    * Gets the Configuration from the File
    * @return configuration
    */
   public Configuration getConfiguration()
   {
      return configuration;
   }
}
