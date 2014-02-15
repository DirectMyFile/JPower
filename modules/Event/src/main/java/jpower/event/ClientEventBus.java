package jpower.event;

import jpower.core.utils.ThreadUtils;
import jpower.socket.Client;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientEventBus extends CustomEventBus {
    private Client client;
    private final InetSocketAddress address;

    public ClientEventBus(String host, int port) {
        address = new InetSocketAddress(host, port);
    }

    public void connect() throws IOException {
        Socket socket = new Socket();
        socket.connect(address);
        client = new Client(socket);
        ThreadUtils.startDaemon(() -> {
            while (!client.getSocket().isClosed()) {
                try {
                    post(client.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void post(Object event) {
        super.post(event);
        if (event instanceof Serializable) {
            try {
                client.writeObject(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
