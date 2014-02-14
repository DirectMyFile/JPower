package jpower.socket;

import jpower.core.Task;
import jpower.core.WorkerPool;
import jpower.core.utils.ThreadUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class WorkerServer {
    private ServerSocket server;
    private InetSocketAddress address;
    private WorkerPool workerPool;
    private ClientHandler clientHandler;
    private Thread acceptThread;
    private boolean shouldRun = false;

    public WorkerServer(String host, int port) throws IOException {
        this.server = new ServerSocket();
        this.workerPool = new WorkerPool(30);
        this.address = new InetSocketAddress(host, port);
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void start() throws IOException {
        this.shouldRun = true;
        server.bind(address);
        this.acceptThread = ThreadUtils.start(new Runnable() {
            @Override
            public void run() {
                while (shouldRun) {
                    workerPool.submit(new Task() {
                        @Override
                        public void execute() {
                            try {
                                clientHandler.handleClient(new Client(server.accept()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public void stop() {
        shouldRun = false;
        workerPool.waitForAll();
    }
}
