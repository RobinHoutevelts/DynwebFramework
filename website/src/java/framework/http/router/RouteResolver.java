package framework.http.router;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import framework.Container;
import framework.http.Controller;
import framework.http.Request;
import framework.http.Response;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RouteResolver implements Resolver {

    private Container app;
    
    private String regex;
    private Class controllerClass;
    private String methodName;
    private List<String> parameters;

    public RouteResolver(Container app, String regex, Class controller, String methodName, List<String> parameters) {
        this.app = app;
        this.regex = regex;
        this.controllerClass = controller;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public void resolve(Request request, Response response) throws ServletException {

        String URI = request.getRequestURI().replaceAll(request.getContextPath(), "");

        Pattern pattern = Pattern.compile(this.regex);
        Matcher matcher = pattern.matcher(URI);

        int groups = matcher.groupCount();

        int i = 0;
        if (matcher.matches()) {
            while (groups > i) {
                String key = this.parameters.get(i);
                String val = matcher.group(i + 1);

                request.addParameter(key, val);
                i++;
            }
        }

        try {
            Constructor constructorClass = this.controllerClass.getConstructor(Container.class);
            Controller controller = (Controller) constructorClass.newInstance(this.app);
            Method method = this.controllerClass.getMethod(this.methodName,Request.class, Response.class);

            method.invoke(controller, request, response);
        }catch( InstantiationException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | IllegalAccessException e) {
            throw new ServletException("Could not resolve Controller '"+ this.controllerClass.getName() + "@" + this.methodName+ "'",e);
        }
        
    }

}
