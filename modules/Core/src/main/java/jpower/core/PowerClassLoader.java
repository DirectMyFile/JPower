package jpower.core;

import jpower.core.internal.PowerInternalSystem;
import jpower.core.utils.ArrayUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.stream.Stream;

@SuppressWarnings("ZeroLengthArrayAllocation")
public class PowerClassLoader extends URLClassLoader {

    private boolean autoload = false;
    private Consumer<Class<?>> onClassLoaded;
    private Consumer<String> onClassFound;

    public PowerClassLoader() {
        this(new URL[0]);
    }

    public PowerClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = loadClass(name, true);

        if (onClassLoaded != null) {
            onClassLoaded.accept(clazz);
        }

        return clazz;
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
        if (autoload) {
            try {
                if (!url.toString().endsWith(".jar")) {
                    return;
                }
                JarInputStream stream = new JarInputStream(url.openStream());
                JarEntry entry;
                while ((entry = stream.getNextJarEntry()) != null) {
                    String entryName = entry.getName();
                    if (entryName.endsWith(".class")) {
                        String className = entryName.replace("/", ".").substring(0, entryName.length() - 6);
                        if (onClassFound != null) {
                            onClassFound.accept(className);
                        }
                        loadClass(className);
                    }
                }
            } catch (IOException | ClassNotFoundException ignored) {
                System.out.println("Failed to autoload!");
            }
        }
    }

    public void enableAutoLoading() {
        autoload = true;
    }

    public Set<Class<?>> getLoadedClasses() {
        Set<Class<?>> classes = new HashSet<>();
        Stream.of(PowerInternalSystem.getLoadedClasses(this)).forEach(classes::add);
        return classes;
    }

    public Collection<URL> getUrls() {
        return ArrayUtils.toList(super.getURLs());
    }

    public void onClassLoaded(Consumer<Class<?>> onClassLoaded) {
        this.onClassLoaded = onClassLoaded;
    }

    public void onClassFound(Consumer<String> onClassFound) {
        this.onClassFound = onClassFound;
    }

    public Set<Class<?>> getClassesImplementing(Class<?> clazz) {
        Set<Class<?>> classes = new HashSet<>();
        classes.forEach(c -> {
            if (c.isAssignableFrom(clazz)) {
                classes.add(c);
            }
        });
        return classes;
    }
}
