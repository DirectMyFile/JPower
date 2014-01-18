package jpower.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Annotation-based Event Bus
 * TODO: Create EventBus Listeners and Dead Event Support
 */
public class EventBus {
    protected final List<RegisteredHandler> handlers;

    public EventBus() {
        this.handlers = new ArrayList<RegisteredHandler>();
    }

    /**
     * Register an Event Handler
     * @param object Object
     */
    public void register(final Object object) {
        handlers.add(new RegisteredHandler(object).setAnnotationType(EventHandler.class).registerMethods());
    }

    /**
     * Unregister an Event Handler
     * @param object Object
     * @return handler was removed
     */
    public boolean unregister(final Object object) {
        RegisteredHandler handlerToRemove = null;
        for (RegisteredHandler handler : handlers) {
            if (handler.getObject() == object) {
                handlerToRemove = handler;
            }
        }
        if (handlerToRemove == null) {
            return false;
        }
        handlers.remove(handlerToRemove);
        return true;
    }

    /**
     * Post an Event to the Bus
     * @param event Event to Post
     */
    public void post(final Object event) {
        for (RegisteredHandler handler : handlers) {
            handler.executeEvent(event);
        }
    }
}
