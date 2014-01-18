package jpower.socket;

import jpower.socket.event.*;
import jpower.socket.exception.InvalidEventException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBus {
    private EventHandler handler;
    private Socket powerSocket;
    private ExecutorService threadPool;

    protected EventBus(Socket powerSocket) {
        this.handler = powerSocket.getEventHandler();
        this.powerSocket = powerSocket;
        this.threadPool = Executors.newFixedThreadPool(500);
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public void handleLine(String line) {
        dispatchEvent(new LineReceivedEvent(powerSocket, line));
    }

    public void dispatchEvent(Event event) {
        threadPool.execute(new EventThread(event));
    }

    public void shutdown() {
        threadPool.shutdownNow();
    }

    public class EventThread implements Runnable {
        private Event event;

        public EventThread(Event event) {
            this.event = event;
        }

        @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
        @Override
        public void run() {
            if (event instanceof DisconnectEvent) {
                handler.onDisconnect((DisconnectEvent) event);
                shutdown();
                return;
            } else if (event instanceof ErrorEvent) {
                if (((ErrorEvent) event).getException().getClass().getName().equals("java.net.ConnectException")) {
                    shutdown();
                }
                handler.onError((ErrorEvent) event);
            } else if (event instanceof ConnectEvent) {
                handler.onConnect((ConnectEvent) event);
            } else if (event instanceof LineSentEvent) {
                handler.onLineSent((LineSentEvent) event);
            } else if (event instanceof LineReceivedEvent) {
                handler.onLineReceived((LineReceivedEvent) event);
            } else {
                handler.onError(new ErrorEvent(0, new InvalidEventException("Socket found an invalid event when trying to dispatch the event of type: " + event.getClass().getName()), powerSocket));
            }
            handler.onEvent(event);
        }
    }
}