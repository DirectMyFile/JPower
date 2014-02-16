package jpower.event;

/**
 * A Custom Event Bus Implementation
 */
public abstract class CustomEventBus extends EventBus {
    public CustomEventBus() {
    }

    public CustomEventBus(boolean globalEnabled) {
        super(globalEnabled);
    }
}
