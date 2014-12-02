package framework.service.providers;

import java.util.List;

import conf.Config;
import framework.Container;
import framework.http.router.Router;
import framework.service.Resolver;

public class RouterServiceProvider implements ServiceProvider {

    @Override
    public void register(Container app) {

        Router router = new Router(app);

        List<Object> ignored = Config.getList("router.ignore");
        
        for (Object ignoreObject : ignored) {
            String ignore = (String) ignoreObject;
            router.addIgnore(ignore);
        }

        app.bind("Router", new Resolver() {
            @Override
            public Object resolve() {
                return router;
            }
        });

    }

}
