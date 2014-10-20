package controller.handlers;

import controller.handlers.ajax.AjaxHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.logging.Level;
import java.util.logging.Logger;

import service.WebService;

public class AjaxHandlerFactory {

    private AjaxHandler[] ajaxHandlers;

    public AjaxHandlerFactory(WebService webService) {

        ajaxHandlers = new AjaxHandler[AjaxHandlers.values().length];

        for (AjaxHandlers ah : AjaxHandlers.values()) {

            AjaxHandler ajaxHandler = null;

            try {
                Class ajaxHandlerClass = Class.forName(ah.getAjaxHandlerClass());
                Constructor constructorClass = ajaxHandlerClass.getConstructor(WebService.class);
                ajaxHandler = (AjaxHandler) constructorClass.newInstance(webService);
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
            ajaxHandlers[ah.ordinal()] = ajaxHandler;
        }
    }

    public AjaxHandler getAjaxHandler(int identifier) {
        return ajaxHandlers[identifier];
    }
}