package jpower.event;

import jpower.core.annotation.Incomplete;
import jpower.socket.Client;
import jpower.socket.ClientHandler;
import jpower.socket.WorkerServer;

import java.io.IOException;

@Incomplete
public class ServerEventBus extends CustomEventBus {
    private WorkerServer workerServer;

    public ServerEventBus(String host, int port) throws IOException {
        workerServer = new WorkerServer(host, port);
        workerServer.setClientHandler(new ClientHandler() {
            @Override
            public void handleClient(Client client) {
                /* TODO: Complete Code for ServerEventBus Client Handling */
            }
        });
    }

    public void start() throws IOException {
        workerServer.start();
    }
}
