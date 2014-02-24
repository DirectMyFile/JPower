package jpower.core.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {
    public static String toString(File file) throws IOException {
        return new String(readBytes(file));
    }

    public static boolean exists(File file) throws IOException {
        return Files.exists(file.toPath());
    }

    public static BufferedReader newReader(File file) throws IOException {
        return Files.newBufferedReader(file.toPath());
    }

    public static byte[] readBytes(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    public static List<String> readLines(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    public static Stream<Path> listFiles(File file) throws IOException {
        return Files.list(file.toPath());
    }

    public static void write(File file, String text) throws IOException {
        write(file, text.getBytes());
    }

    public static void write(File file, byte[] input) throws IOException {
        Files.write(file.toPath(), input);
    }

    public static BufferedWriter newWriter(File file) throws IOException {
        return Files.newBufferedWriter(file.toPath());
    }
}
