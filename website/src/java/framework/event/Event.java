package framework.event;

import java.util.Collection;

public class Event {

    private String eventName;
    private Bag eventBag;

    public Event(String name) {
        this.eventName = name;
        this.eventBag = new Bag();
    }

    public Event(String name, Bag bag) {
        this.eventName = name;
        this.eventBag = bag;
    }

    public String getEventName() {
        return this.eventName;
    }

    public Bag getBag() {
        return this.eventBag;
    }

    public Object getAttribute(String name) {
        return this.eventBag.getAttribute(name);
    }

    public Collection<String> getAttributeNames() {
        return this.eventBag.getAttributeNames();
    }

    public void setAttribute(String name, Object object) {
        this.eventBag.setAttribute(name, object);
    }

}
