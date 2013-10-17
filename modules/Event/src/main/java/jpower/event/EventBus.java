package jpower.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Fast Event Bus (Not Threaded)
 */
public class EventBus {
    private List<RegisteredHandler> handlers = new ArrayList<RegisteredHandler>();

    /**
     * Register an Event Handler
     * @param object Object
     */
    public void register(Object object) {
        handlers.add(new RegisteredHandler(object));
    }

    /**
     * Unregister an Event Handler
     * @param object Object
     * @return handler was removed
     */
    public boolean unregister(Object object) {
        RegisteredHandler handlerToRemove = null;
        for (RegisteredHandler handler : handlers) {
            if (handler.getObject()==object) {
                handlerToRemove = handler;
            }
        }
        if (handlerToRemove==null) {
            return false;
        }
        handlers.remove(handlerToRemove);
        return true;
    }

    /**
     * Post an Event to the Bus
     * @param event Event to Post
     */
    public void post(Object event) {
        for (RegisteredHandler handler : handlers) {
            handler.executeEvent(event);
        }
    }
}
