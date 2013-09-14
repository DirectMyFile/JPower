package jpower.socket;

public class JPowerSocketBuilder {
    private String host;
    private int port;
    private EventHandler eventHandler;

    public JPowerSocketBuilder() {

    }

    public JPowerSocket create() {
        return new JPowerSocket(this);
    }

    public JPowerSocketBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public JPowerSocketBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public JPowerSocketBuilder setEventHandler(EventHandler eventHandler) {
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
