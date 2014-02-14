package jpower.event;

import jpower.core.IEvent;

class DeadEvent implements IEvent {
    private final Object event;

    public DeadEvent(Object event) {
        this.event = event;
    }

    public Object getEvent() {
        return event;
    }
}
