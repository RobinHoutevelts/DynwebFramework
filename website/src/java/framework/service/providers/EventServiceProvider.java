package framework.service.providers;

import framework.Container;
import framework.event.Emitter;
import framework.event.EventEmitter;
import framework.service.Resolver;

public class EventServiceProvider implements ServiceProvider {

    @Override
    public void register(Container app) {
        
        // Aanmaken EvenEmitter
        Emitter eventEmitter = new EventEmitter();

        // EventEmitter toevoegen aan IoC container
        app.bind("EventEmitter", new Resolver() {

            @Override
            public Object resolve() {
                return eventEmitter;
            }
        });
    }

}
