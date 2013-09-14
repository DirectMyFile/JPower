package jpower.socket;

import jpower.socket.event.ConnectEvent;
import jpower.socket.event.ErrorEvent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketHandler {
    private Socket socket;
    private InputHandler inputHandler;
    private OutputHandler outputHandler;
    private InetSocketAddress address;
    private JPowerSocket powerSocket;

    public SocketHandler(String host, int port) {
        socket = new Socket();
        address = new InetSocketAddress(host, port);
    }

    protected void setPowerSocket(JPowerSocket powerSocket) {
        this.powerSocket = powerSocket;
    }

    public void connect() {
        try {
            socket.connect(address);
            inputHandler = new InputHandler(this);
            outputHandler = new OutputHandler(this);
            inputHandler.start();
            getEventBus().dispatchEvent(new ConnectEvent(powerSocket));
        } catch (IOException e) {
            getEventBus().dispatchEvent(new ErrorEvent(1, e, powerSocket));
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            getEventBus().dispatchEvent(new ErrorEvent(5, e, powerSocket));
        }
    }

    public boolean isConnected() {
        return getSocket().isConnected();
    }

    public boolean isClosed() {
        return getSocket().isClosed();
    }

    public void sendLine(String line) {
        outputHandler.send(line);
    }

    public Socket getSocket() {
        return socket;
    }

    public EventBus getEventBus() {
        return powerSocket.getEventBus();
    }

    public JPowerSocket getPowerSocket() {
        return powerSocket;
    }
}
