package jpower.socket;

import jpower.socket.event.ConnectEvent;
import jpower.socket.event.ErrorEvent;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Handles all socket related Functions
 */
public class SocketHandler {
    private java.net.Socket socket;
    private InputHandler inputHandler;
    private OutputHandler outputHandler;
    private InetSocketAddress address;
    private Socket powerSocket;

    public SocketHandler(String host, int port) {
        socket = new java.net.Socket();
        address = new InetSocketAddress(host, port);
    }

    /**
     * Sets the Socket Instance
     * @param powerSocket Socket
     */
    protected void setPowerSocket(Socket powerSocket) {
        this.powerSocket = powerSocket;
    }

    public void connect() {
        if (socket.isClosed()) socket = new java.net.Socket();
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

    public void sendBytes(byte[] bytes) {
        outputHandler.send(bytes);
    }

    public java.net.Socket getSocket() {
        return socket;
    }

    public EventBus getEventBus() {
        return powerSocket.getEventBus();
    }

    public Socket getPowerSocket() {
        return powerSocket;
    }
}
