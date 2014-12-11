package services;

import framework.Container;
import framework.http.router.Router;
import framework.service.providers.ServiceProvider;

public class RoutesServiceProvider implements ServiceProvider {

    @Override
    public void register(Container app) {
        Router router = (Router) app.make("Router");

        
        
        try{
            
            router.get( "users.index",   "/users",               "controller.UsersController@index");
            router.get( "users.create",  "/users/create",        "controller.UsersController@create");
            router.post("users.store",   "/users/store",         "controller.UsersController@store");
            router.get( "users.show",    "/users/:userId",       "controller.UsersController@show");
            router.get( "users.edit",    "/users/:userId/edit",  "controller.UsersController@edit");
            router.post("users.update",  "/users/:userId",       "controller.UsersController@update");
            router.post("users.delete",  "/users/:userId",       "controller.UsersController@delete");
            
        }catch(Exception e){
            e.getStackTrace();
        }
    }

}
