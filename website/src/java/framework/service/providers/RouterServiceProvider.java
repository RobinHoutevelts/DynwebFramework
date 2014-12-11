package framework.service.providers;

import framework.Container;
import framework.http.router.Router;
import framework.service.Resolver;

public class RouterServiceProvider implements ServiceProvider {

    @Override
    public void register(Container app) {

        Router router = new Router(app);

        app.bind("Router", new Resolver() {
            @Override
            public Object resolve() {
                return router;
            }
        });
    }

}
