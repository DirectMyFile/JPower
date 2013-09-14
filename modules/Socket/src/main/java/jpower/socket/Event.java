package jpower.socket;

public class Event {

    protected JPowerSocket powerSocket;

    public Event(JPowerSocket powerSocket) {
        this.powerSocket = powerSocket;
    }

    /**
     * Get the JPowerSocket instance
      * @return the PowerSocket source of the event
     */
    public JPowerSocket getPowerSocket() {
        return powerSocket;
    }

}
