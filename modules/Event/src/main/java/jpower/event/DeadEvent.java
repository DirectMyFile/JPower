package jpower.event;

class DeadEvent {
    private final Object event;

    public DeadEvent(Object event) {
        this.event = event;
    }

    public Object getEvent() {
        return event;
    }
}
