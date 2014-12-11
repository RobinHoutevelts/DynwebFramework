package framework.service.providers;

import framework.Container;
import framework.html.Router;
import framework.service.Resolver;

public class HtmlRouteHelperServiceProvider implements ServiceProvider {

    @Override
    public void register(Container app) {
        framework.http.router.Router router = (framework.http.router.Router) app.make("Router");

        Router htmlRouter = new Router(router);
        
        // HtmlRouter aan ServletContext hangen zodat deze beschikbaar is vanuit de views
        app.getContext().setAttribute("Router", htmlRouter);
                
        app.bind("HtmlRouter", new Resolver() {
            @Override
            public Object resolve() {
                return htmlRouter;
            }
        });
        
    }
    
    

}
