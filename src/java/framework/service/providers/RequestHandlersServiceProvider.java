package framework.service.providers;

import controller.handlers.AjaxHandlerFactory;
import controller.handlers.RequestHandlerFactory;
import framework.Container;
import framework.service.Resolver;

public class RequestHandlersServiceProvider implements ServiceProvider {

  @Override
  public void register(Container app) {
    AjaxHandlerFactory ajaxHandlerFactory = new AjaxHandlerFactory(app);
    RequestHandlerFactory requestHandlerFactory = new RequestHandlerFactory(app);
    
    app.bind("AjaxHandlerFactory", new Resolver() {
      @Override
      public Object resolve() {
        return ajaxHandlerFactory;
      }
    });

    app.bind("RequestHandlerFactory", new Resolver() {
      @Override
      public Object resolve() {
        return requestHandlerFactory;
      }
    });
  }

  
}
