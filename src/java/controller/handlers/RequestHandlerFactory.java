package controller.handlers;

import controller.handlers.request.RequestHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.logging.Level;
import java.util.logging.Logger;

import service.WebService;

public class RequestHandlerFactory {
    
    private RequestHandler[] requestHandlers;
    
    public RequestHandlerFactory(WebService webService) {
        
        requestHandlers = new RequestHandler[RequestHandlers.values().length];
        
        for (RequestHandlers rh : RequestHandlers.values()) {
            
            RequestHandler requestHandler = null;
            
            try {
                Class requestHandlerClass = Class.forName(rh.getRequestHandlerClass());
                Constructor constructorClass = requestHandlerClass.getConstructor(WebService.class, String.class);
                requestHandler = (RequestHandler) constructorClass.newInstance(webService, rh.getView());
            }
            catch (ClassNotFoundException   | // Thrown by 'Class.forName(String)' if the class cannot be located.
                   NoSuchMethodException    | // Thrown by 'Class.getConstructor(paraps...)' if a matching method is not found.
                   SecurityException        | // Thrown by 'Class.getConstructor(params...)'. Check api for more information!
                   InstantiationException   | // Thrown by 'Contructor.newInstance(params...) if the class that declares the constructor represents an abstract class.
                   IllegalAccessException   | // Thrown by 'Contructor.newInstance(params...). Check api for more information!
                   IllegalArgumentException | // Thrown by 'Contructor.newInstance(params...)'. Check api for more information!
                   InvocationTargetException  // Thrown by 'Contructor.newInstance(params...)' if the underlying constructor throws an exception. 
                   exception) {
                Logger.getLogger(RequestHandlerFactory.class.getName()).log(Level.SEVERE, null, exception);
            }
            requestHandlers[rh.ordinal()] = requestHandler;
        }
    }
    
    public RequestHandler getRequestHandler(int identifier) {
        return requestHandlers[identifier];
    }
}