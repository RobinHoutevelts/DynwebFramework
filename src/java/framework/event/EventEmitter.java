package framework.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventEmitter implements Emitter {

    private HashMap<String, ArrayList<EventHandler>> eventListeners = new HashMap<String, ArrayList<EventHandler>>();

    @Override
    public void fire(Event event) {

        String eventName = event.getEventName();
        List<EventHandler> handlers = this.eventListeners.get(eventName);
        for (EventHandler handler : handlers) {
            handler.handle(event);
        }

    }

    @Override
    public void listen(String eventName, EventHandler handler) {

        ArrayList<EventHandler> handlers;

        if (this.eventListeners.containsKey(eventName)) {
            handlers = this.eventListeners.get(eventName);
        } else {
            handlers = new ArrayList<EventHandler>();
            this.eventListeners.put(eventName, handlers);
        }

        handlers.add(handler);
    }

}
