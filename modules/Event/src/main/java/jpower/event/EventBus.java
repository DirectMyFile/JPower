package jpower.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Simple Annotation-based Event Bus
 */
public class EventBus {
    final List<RegisteredHandler> handlers;

    public EventBus() {
        handlers = new ArrayList<>();
    }

    /**
     * Register an Event Handler
     *
     * @param object Object
     */
    public void register(final Object object) {
        handlers.add(new RegisteredHandler(object).setAnnotationType(EventHandler.class).registerMethods());
    }

    /**
     * Unregister an Event Handler
     *
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
     *
     * @param event Event to Post
     */
    public void post(Object event) {
        boolean didRun = false;
        for (RegisteredHandler handler : handlers) {
            if (handler.executeEvent(event)) {
                didRun = true;
            }
        }
        if (!event.getClass().isAssignableFrom(DeadEvent.class) && !didRun) {
            post(new DeadEvent(event));
        }
    }
}
