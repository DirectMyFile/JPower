package jpower.socket;

import jpower.core.IEvent;

public class Event implements IEvent {

    protected Socket powerSocket;

    public Event(Socket powerSocket) {
        this.powerSocket = powerSocket;
    }

    /**
     * Get the Socket instance
      * @return the PowerSocket source of the event
     */
    public Socket getPowerSocket() {
        return powerSocket;
    }

}
