package jpower.core;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collection;

public class PowerClassLoader extends URLClassLoader {

    public PowerClassLoader() {
        this(new URL[1]);
    }

    public PowerClassLoader(URL[] urls) {
        super(urls);
    }

    public void addURL(URL url) {
        super.addURL(url);
    }

    public Collection<URL> getURLS() {
        return Arrays.asList(getURLs());
    }
}
