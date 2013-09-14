package jpower.socket;

import jpower.socket.event.DisconnectEvent;
import jpower.socket.event.ErrorEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHandler implements Runnable {
    private SocketHandler handler;
    private BufferedReader reader;
    private Thread thread;
    private EventBus eventBus;

    public InputHandler(SocketHandler handler) {
        this.handler = handler;
        try {
            this.reader = new BufferedReader(new InputStreamReader(handler.getSocket().getInputStream()));
            this.eventBus = handler.getEventBus();
        } catch (IOException e) {
            eventBus.dispatchEvent(new ErrorEvent(2, e, handler.getPowerSocket()));
        }
    }

    public void stop() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                eventBus.handleLine(line);
            }
        } catch (IOException e) {
           eventBus.dispatchEvent(new ErrorEvent(3, e, handler.getPowerSocket()));
        }
        try {
            reader.close();
            handler.getSocket().close();
        } catch (IOException e) {
            eventBus.dispatchEvent(new ErrorEvent(6, e, handler.getPowerSocket()));
        }
        eventBus.dispatchEvent(new DisconnectEvent(handler.getPowerSocket()));
    }

    public SocketHandler getHandler() {
        return handler;
    }
}
