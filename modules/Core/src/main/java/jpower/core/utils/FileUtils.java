package jpower.core.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class FileUtils {
   public static String toString(File file) throws IOException {
      return new String(readBytes(file));
   }

   public static boolean exists(File file) {
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

   public static void write(File file, List<String> lines) throws IOException {
      write(file, ListUtils.toString(lines));
   }

   public static void write(File file, byte[] input) throws IOException {
      Files.write(file.toPath(), input);
   }

   public static BufferedWriter newWriter(File file) throws IOException {
      return Files.newBufferedWriter(file.toPath());
   }

   public static void watch(File file, Function<WatchEvent<?>, Boolean> handler, WatchEvent.Kind<?>... kinds) throws IOException {
      WatchService watcher = FileSystems.getDefault().newWatchService();
      Path path = file.toPath();
      WatchKey key = path.register(watcher, kinds);
      exit:
      while (true) {
         for (WatchEvent<?> event : key.pollEvents()) {
            if (!handler.apply(event)) {
               break exit;
            }
         }
         boolean valid = key.isValid();
         if (!valid) {
            break;
         }
      }
   }
}
