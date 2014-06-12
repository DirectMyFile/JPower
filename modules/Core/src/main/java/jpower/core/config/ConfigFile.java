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
   private final File file;
   private final Configuration configuration = new Configuration();

   /**
    * Creates a {@link ConfigFile} from the Path
    *
    * @param path the path to load the configuration from
    */
   public ConfigFile(Path path)
   {
      this(path.toFile());
   }

   /**
    * Creates a {@link ConfigFile} from the file
    * @param file the file to load the configuration from
    */
   public ConfigFile(File file)
   {
      this.file = file;
   }

   /**
    * Loads the configuration file
    */
   @Override
   public void load()
   {
      try
      {
         configuration.load(file);
      }
      catch (IOException e)
      {
         ExceptionUtils.throwUnchecked(e);
      }
   }

   /**
    * Reloads the configuration file
    */
   @Override
   public void reload()
   {
      configuration.reset();
      load();
   }

   /**
    * Gets the {@link Configuration} from the File
    * @return configuration
    */
   public Configuration getConfiguration()
   {
      return configuration;
   }
}
