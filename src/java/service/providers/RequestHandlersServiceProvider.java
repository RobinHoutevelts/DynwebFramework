package service.providers;

import service.IoCResolver;

import service.IoC;
import controller.handlers.AjaxHandlerFactory;
import controller.handlers.RequestHandlerFactory;

public class RequestHandlersServiceProvider implements ServiceProvider {

  @Override
  public void register(IoC app) {
    AjaxHandlerFactory ajaxHandlerFactory = new AjaxHandlerFactory(app);
    RequestHandlerFactory requestHandlerFactory = new RequestHandlerFactory(app);
    
    app.bind("AjaxHandlerFactory", new IoCResolver() {
      @Override
      public Object resolve() {
        return ajaxHandlerFactory;
      }
    });

    app.bind("RequestHandlerFactory", new IoCResolver() {
      @Override
      public Object resolve() {
        return requestHandlerFactory;
      }
    });
  }

  
}
