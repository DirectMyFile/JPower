package jpower.core.utils;

import java.io.*;

public class IOUtils {
    /**
     * Retrieves a Resource as a String if exists, else returns null
     * @param resource resource name
     * @return resource as string
     */
    public static String getResourceAsString(Class<?> clazz, String resource) {
        InputStream stream = clazz.getResourceAsStream(resource);
        if (stream == null)
            return null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringWriter writer = new StringWriter();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                writer.append('\n').append(line);
            }
            writer.getBuffer().replace(0, 1, ""); // Gets rid of newline at beginning
        } catch (IOException e) {
            return null;
        }
        return writer.toString();
    }
}
