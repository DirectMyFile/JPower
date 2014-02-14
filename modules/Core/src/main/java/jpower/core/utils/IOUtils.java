package jpower.core.utils;

import java.io.*;

public final class IOUtils {
    /**
     * Retrieves a Resource as a String if exists, else returns null
     *
     * @param resource resource name
     * @return resource as string
     */
    public static String getResourceAsString(Class<?> clazz, String resource) {
        InputStream stream = clazz.getResourceAsStream(resource);
        if (stream == null)
            return null;
        return toString(stream);
    }

    public static String toString(InputStream stream) {
        BufferedReader reader = createBufferedReader(stream);
        StringWriter writer = new StringWriter();
        int b;
        try {
            while ((b = reader.read()) != -1) {
                writer.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return writer.toString();
    }

    public static BufferedReader createBufferedReader(InputStream stream) {
        return new BufferedReader(new InputStreamReader(stream));
    }
}
