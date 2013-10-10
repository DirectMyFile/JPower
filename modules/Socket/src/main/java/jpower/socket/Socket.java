package jpower.socket;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Socket {
    private SocketBuilder builder;
    private SocketHandler socketHandler;
    private EventHandler handler;
    private EventBus eventBus;
    private final Logger logger = Logger.getLogger(getClass().getName());

    protected Socket(SocketBuilder builder) {
        this.builder = builder;
        setup();
    }

    public Socket(String host, int port, EventHandler handler) {
        builder = new SocketBuilder().setHost(host).setPort(port).setEventHandler(handler);
    }

    public Logger getLogger() {
        return logger;
    }

    private void setup() {
        handler = builder.getEventHandler();
        eventBus = new EventBus(this);
        socketHandler = new SocketHandler(builder.getHost(), builder.getPort());
        socketHandler.setPowerSocket(this);
        logger.setLevel(Level.OFF);
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public EventHandler getEventHandler() {
        return handler;
    }

    public SocketHandler getSocketHandler() {
        return socketHandler;
    }

    public void send(String line) {
        socketHandler.sendLine(line);
    }

    public void send(byte[] bytes) {
        socketHandler.sendBytes(bytes);
    }

    public void connect() {
        socketHandler.connect();
    }

    public void disconnect() {
        socketHandler.disconnect();
    }

    public void setLogLevel(Level level) {
        logger.setLevel(level);
    }

    public boolean isConnected() {
        return getSocketHandler().isConnected();
    }
}
