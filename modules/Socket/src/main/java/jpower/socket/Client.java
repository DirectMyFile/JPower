package jpower.socket;

import jpower.core.utils.IOUtils;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.function.Consumer;

public class Client {
   private final Socket socket;
   private final BufferedReader reader;
   private final PrintWriter writer;
   private final ObjectOutputStream objectOut;
   private final ObjectInputStream objectIn;

   public Client(Socket socket) throws IOException {
      this.socket = socket;
      reader = IOUtils.createBufferedReader(socket.getInputStream());
      writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      objectOut = new ObjectOutputStream(socket.getOutputStream());
      objectIn = new ObjectInputStream(socket.getInputStream());
   }

   public void write(String text) {
      writer.write(text);
      writer.flush();
   }

   public void writeObject(Object object) throws IOException {
      objectOut.writeObject(object);
      objectOut.flush();
   }

   public SocketAddress address() {
      return socket.getRemoteSocketAddress();
   }

   public void writeLine(String line) {
      write(line + System.lineSeparator());
   }

   public Object readObject() throws IOException, ClassNotFoundException {
      return objectIn.readObject();
   }

   public String readLine() throws IOException {
      return reader.readLine();
   }

   public Socket socket() {
      return socket;
   }

   public void end() throws IOException {
      socket.close();
   }

   public boolean closed() {
      return socket().isClosed();
   }

   public void eachLine(Consumer<String> consumer) throws IOException {
      String line;
      while ((line = readLine()) != null) {
         consumer.accept(line);
      }
   }
}
