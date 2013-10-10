package jpower.event;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private List<RegisteredHandler> handlers = new ArrayList<RegisteredHandler>();

    public void register(Object object) {
        handlers.add(new RegisteredHandler(object));
        System.out.println("Registered Handlers");
    }

    public void post(Object event) {
        for (RegisteredHandler handler : handlers) {
            handler.executeEvent(event);
        }
    }
}
