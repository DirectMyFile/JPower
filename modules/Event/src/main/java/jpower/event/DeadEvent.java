package jpower.event;

import jpower.core.IEvent;

public class DeadEvent implements IEvent {
    private Object event;

    public DeadEvent(Object event) {
        this.event = event;
    }

    public Object getEvent() {
        return event;
    }
}
