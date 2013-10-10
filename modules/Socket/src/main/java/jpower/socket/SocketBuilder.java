package jpower.socket;

public class SocketBuilder {
    private String host;
    private int port;
    private EventHandler eventHandler;

    public SocketBuilder() {

    }

    public Socket create() {
        return new Socket(this);
    }

    public SocketBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public SocketBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public SocketBuilder setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        return this;
    }

    protected String getHost() {
        return host;
    }

    protected int getPort() {
        return port;
    }

    protected EventHandler getEventHandler() {
        return eventHandler;
    }
}
