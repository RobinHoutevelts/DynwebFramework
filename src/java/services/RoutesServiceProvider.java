package services;

import framework.Container;
import framework.http.router.Router;
import framework.service.providers.ServiceProvider;

public class RoutesServiceProvider implements ServiceProvider {

    @Override
    public void register(Container app) {
        Router router = (Router) app.make("Router");

        try {
            // Enter routes here
            router.get("home", "/", "controller.HomeController@index");
            router.get("test", "/test/:id", "controller.HomeController@test");

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}
