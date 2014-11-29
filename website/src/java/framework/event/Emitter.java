package framework.event;

public interface Emitter {

    public void fire(Event event);

    public void listen(String eventName, EventHandler handler);

}
