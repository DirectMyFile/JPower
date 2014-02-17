package jpower.core.utils;

import java.io.*;
import java.nio.file.Files;

public class IOUtils {
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
            stream.close();
        } catch (IOException e) {
            return null;
        }
        return writer.toString();
    }

    public static byte[] getBytes(InputStream stream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte b;
        while ((b = (byte) stream.read()) != -1) {
            buffer.write(b);
        }
        return buffer.toByteArray();
    }

    public static void write(byte[] bytes, File file) throws IOException {
        Files.write(file.toPath(), bytes);
    }

    public static BufferedReader createBufferedReader(InputStream stream) {
        return new BufferedReader(new InputStreamReader(stream));
    }
}
