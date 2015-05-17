package jpower.socket;

import jpower.core.Factory;
import jpower.core.utils.ExceptionUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientFactory implements Factory<Client> {

   private final InetSocketAddress address;

   public ClientFactory(String host, int port) {
      address = new InetSocketAddress(host, port);
   }

   public static Client create(String host, int port) {
      return new ClientFactory(host, port).create();
   }

   @Override
   public Client create() {
      Socket socket = new Socket();
      try {
         socket.connect(address);
      } catch (IOException e) {
         ExceptionUtils.throwUnchecked(e);
         return null;
      }
      try {
         return new Client(socket);
      } catch (IOException e) {
         ExceptionUtils.throwUnchecked(e);
         return null;
      }
   }
}
