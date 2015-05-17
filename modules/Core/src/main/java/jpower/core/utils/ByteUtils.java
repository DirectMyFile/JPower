package jpower.core.utils;

public class ByteUtils {
   public static byte[] reverse(byte[] original) {
      return unwrap(ArrayUtils.reverse(wrap(original)));
   }

   public static Byte[] wrap(byte[] bytes) {
      Byte[] Bytes = new Byte[bytes.length];
      for (int i = 0; i < bytes.length; i++) {
         Bytes[i] = bytes[i];
      }
      return Bytes;
   }

   public static byte[] unwrap(Byte[] Bytes) {
      byte[] bytes = new byte[Bytes.length];
      for (int i = 0; i < bytes.length; i++) {
         bytes[i] = Bytes[i];
      }
      return bytes;
   }
}
