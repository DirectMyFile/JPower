package jpower.core;

import jpower.core.internal.PowerInternalSystem;
import jpower.core.utils.ArrayUtils;
import jpower.core.utils.ListUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * A new ClassLoader built upon URLClassLoader
 * and adds some useful events for class loads.
 */
@SuppressWarnings("ZeroLengthArrayAllocation")
public class PowerClassLoader extends URLClassLoader
{

   private boolean autoload;
   private Consumer<Class<?>> onClassLoaded;
   private Consumer<String> onClassFound;

   public PowerClassLoader()
   {
      this(new URL[0]);
   }

   public PowerClassLoader(URL[] urls)
   {
      super(urls);
   }

   @Override
   public Class<?> loadClass(String name) throws ClassNotFoundException
   {
      Class<?> clazz = loadClass(name, true);

      if (onClassLoaded != null)
      {
         onClassLoaded.accept(clazz);
      }

      return clazz;
   }

   @Override
   public void addURL(URL url)
   {
      super.addURL(url);
      if (autoload)
      {
         try
         {
            if (!url.toString().endsWith(".jar"))
            {
               return;
            }
            JarInputStream stream = new JarInputStream(url.openStream());
            JarEntry entry;
            while ((entry = stream.getNextJarEntry()) != null)
            {
               String entryName = entry.getName();
               if (entryName.endsWith(".class"))
               {
                  String className = entryName.replace("/", ".").substring(0, entryName.length() - 6);
                  if (onClassFound != null)
                  {
                     onClassFound.accept(className);
                  }
                  loadClass(className);
               }
            }
         } catch (IOException | ClassNotFoundException e)
         {
            throw new RuntimeException("Failed to auto-load classes.", e);
         }
      }
   }

   public void enableAutoLoading()
   {
      autoload = true;
   }

   public void disableAutoLoading()
   {
      autoload = false;
   }

   public Set<Class<?>> getLoadedClasses()
   {
      return ListUtils.toSet(ArrayUtils.toList(PowerInternalSystem.getLoadedClasses(this)));
   }

   public Collection<URL> getUrls()
   {
      return ArrayUtils.toList(super.getURLs());
   }

   public void onClassLoaded(Consumer<Class<?>> onClassLoaded)
   {
      this.onClassLoaded = onClassLoaded;
   }

   public void onClassFound(Consumer<String> onClassFound)
   {
      this.onClassFound = onClassFound;
   }

   public Set<Class<?>> getClassesImplementing(Class<?> clazz)
   {
      Set<Class<?>> classes = new HashSet<>();
      getLoadedClasses().stream().filter(c -> c.isAssignableFrom(clazz)).forEach(classes::add);
      return classes;
   }

   public boolean isAutoLoadEnabled()
   {
      return autoload;
   }
}
