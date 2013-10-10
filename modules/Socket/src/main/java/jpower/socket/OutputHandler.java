package jpower.socket;

import jpower.socket.event.ErrorEvent;
import jpower.socket.event.LineSentEvent;

import java.io.IOException;
import java.io.PrintStream;

public class OutputHandler {
    private PrintStream stream;
    private SocketHandler handler;
    private EventBus eventBus;

    public OutputHandler(SocketHandler handler) {
        this.handler = handler;
        this.eventBus = handler.getEventBus();
        setup();
    }

    private void setup() {
        try {
            this.stream = new PrintStream(handler.getSocket().getOutputStream());
        } catch (IOException e) {
            eventBus.dispatchEvent(new ErrorEvent(4, e, handler.getPowerSocket()));
        }
    }

    public void send(String line) {
        stream.println(line);
        stream.flush();
        eventBus.dispatchEvent(new LineSentEvent(handler.getPowerSocket(), line));
    }

    public void send(byte[] bytes) {
        try {
            stream.write(bytes);
            eventBus.dispatchEvent(new LineSentEvent(handler.getPowerSocket(), new String(bytes)));
        } catch (IOException e) {
            eventBus.dispatchEvent(new ErrorEvent(6, e, getSocketHandler().getPowerSocket()));
        }
    }

    public SocketHandler getSocketHandler() {
        return handler;
    }
}
