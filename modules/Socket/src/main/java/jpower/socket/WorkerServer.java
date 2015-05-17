package jpower.socket;

import jpower.core.AdvancedService;
import jpower.core.WorkerPool;
import jpower.core.utils.ThreadUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class WorkerServer implements AdvancedService {
   private final ServerSocket server;
   private final InetSocketAddress address;
   private final WorkerPool workerPool;
   private ClientHandler clientHandler;
   @SuppressWarnings("FieldCanBeLocal")
   private Thread acceptThread;
   private boolean shouldRun;

   public WorkerServer(String host, int port) throws IOException {
      server = new ServerSocket();
      workerPool = new WorkerPool(30);
      address = new InetSocketAddress(host, port);
   }

   public void setClientHandler(ClientHandler clientHandler) {
      this.clientHandler = clientHandler;
   }

   @Override
   public void start() throws IOException {
      shouldRun = true;
      server.bind(address);
      acceptThread = ThreadUtils.start(() -> {
         while (shouldRun) {
            workerPool.submit(() -> {
               try {
                  clientHandler.handleClient(new Client(server.accept()));
               } catch (IOException ignored) {
               }
            });
         }
      });
   }

   @Override
   public void stop() {
      shouldRun = false;
      workerPool.waitForAll();
   }
}
