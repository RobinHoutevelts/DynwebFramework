package framework.http.router;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.ServletException;

import framework.Container;
import framework.http.Request;
import framework.http.Response;
import framework.http.controllers.Controller;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RouteResolver implements Resolver {

    private Container app;
    private Route route;
    
    public RouteResolver(Container app, Route route){
        this.app = app;
        this.route = route;
    }

    public void resolve(Request request, Response response) throws ServletException {

        String url = request.getRequestURI().replaceAll(request.getContextPath(), "");
        
        Class controllerClass = this.route.getControllerClass();
        String methodName = this.route.getMethodName();
        
        HashMap<String, String> parameters = this.route.extractParameters(url);
        for(String key : parameters.keySet()){
            String value = parameters.get(key);
            
            request.addParameter(key, value);
        }

        try {
            Constructor constructorClass = controllerClass.getConstructor(Container.class);
            Controller controller = (Controller) constructorClass.newInstance(this.app);
            controller.setRequest(request);
            controller.setResponse(response);
            Method method = controllerClass.getMethod(methodName);

            method.invoke(controller);
        }catch( InstantiationException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | IllegalAccessException e) {
            throw new ServletException("Error executing '"+ controllerClass.getName() + "@" + methodName+ "'",e);
        }
        
    }

}
