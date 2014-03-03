package jpower.event;

import jpower.socket.Client;
import jpower.socket.ClientHandler;
import jpower.socket.WorkerServer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public class ServerEventBus extends CustomEventBus {
    private final WorkerServer workerServer;
    private final Collection<Client> clients = new LinkedList<>();

    public ServerEventBus(String host, int port) throws IOException {
        workerServer = new WorkerServer(host, port);
        workerServer.setClientHandler(new ClientHandler() {
            @Override
            public void handleClient(Client client) {
                clients.add(client);
                while (!client.socket().isClosed()) {
                    try {
                        post(client.readObject());
                    } catch (IOException | ClassNotFoundException ignored) {
                    }
                }
            }
        });
    }

    public void start() throws IOException {
        workerServer.start();
    }

    @Override
    public void post(Object event) {
        super.post(event);
        if (event instanceof Serializable) {
            clients.forEach(client -> {
                try {
                    client.writeObject(event);
                } catch (IOException ignored) {
                }
            });
        }
    }
}
