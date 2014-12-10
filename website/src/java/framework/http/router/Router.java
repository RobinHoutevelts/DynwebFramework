package framework.http.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framework.Container;

@SuppressWarnings("rawtypes")
public class Router {
    
    protected List<String> ignored = new ArrayList<String>();
    
    protected HashMap<Route, Resolver> routes = new HashMap<Route, Resolver>();
    protected Container app;
    
    public Router(Container app){
        this.app =  app;
    }
    
    public void addIgnore(String string)
    {
        this.ignored.add(string);
    }
    
    public Route get(String name, String url, String action) throws Exception {
        ArrayList<String> supportedHttpMethods = new ArrayList<String>();
        supportedHttpMethods.add("get");
        
        Route route = createRoute(name, url, action, supportedHttpMethods);
        Resolver resolver = new RouteResolver(this.app, route);
        
        this.addRoute(route, resolver);
        
        return route;
    }
    
    public Route post(String name, String url, String action) throws Exception {
        ArrayList<String> supportedHttpMethods = new ArrayList<String>();
        supportedHttpMethods.add("post");
        
        Route route = createRoute(name, url, action, supportedHttpMethods);
        Resolver resolver = new RouteResolver(this.app, route);
        
        this.addRoute(route, resolver);
        
        return route;
    }
    
    public Route both(String name, String url, String action) throws Exception{
        ArrayList<String> supportedHttpMethods = new ArrayList<String>();
        supportedHttpMethods.add("get");
        supportedHttpMethods.add("post");
        
        Route route = createRoute(name, url, action, supportedHttpMethods);
        Resolver resolver = new RouteResolver(this.app, route);
        
        this.addRoute(route, resolver);
        
        return route;
    }

    public void addRoute(Route route, Resolver resolver){
        this.routes.put(route, resolver);
    }

    private Route createRoute(String name, String url, String action, List<String> supportedHttpMethods)
            throws Exception {
        String regex = this.regexify(url);
        
        Class controllerClass = this.getControllerClass(action);
        String methodName         = this.getMethodName(action);
        
        Route route = new Route(name, regex, controllerClass, methodName, supportedHttpMethods);
        return route;
    }
    
    public Route getRouteByURL(String url){
        return this.getRouteByURL(url, "get");
    }
    
    public Route getRouteByURL(String url, String httpMethod)
    {
        Route route = null;
        
        for(Route r : this.routes.keySet()){
            if(r.urlMatches(url) && r.supportsHttpMethod(httpMethod)){
                route = r;
                break;
            }
        }
        
        return route;
    }
    
    public Route getRouteByName(String name){
        return this.getRouteByName(name, "get");
    }
    
    public Route getRouteByName(String name, String httpMethod)
    {
        Route route = null;
        
        for(Route r : this.routes.keySet()){
            if(r.getName().equals(name) && r.supportsHttpMethod(httpMethod)){
                route = r;
                break;
            }
        }
        
        return route;
    }
    
    public Resolver getResolver(Route route){
        Resolver resolver = null;

        if(route != null)
            resolver = this.routes.get(route);
        
        return resolver;
    }
    
    private String regexify(String url) throws Exception{
        url = this.trimUrl(url);
 
        Pattern pattern = Pattern.compile(":(\\w+)");
        Matcher matcher = pattern.matcher(url);
            
        url = matcher.replaceAll("([^/]+)");
        
        url = url+"/?$";

        return url;
    }
    
    private String trimUrl(String url){
        url = url.trim();
                
        for(String ignore : this.ignored){
            url = url.replaceAll(ignore, "");
        }
                
        // Change "/foo/bar/baz/" to /foo/bar/baz
        // Also   "//foo/bar/baz//"
        String tmp = url;
        do{
            url = tmp;
            tmp = url.replaceAll("^/|/$", "");
        }while(!url.equals(tmp));
        tmp = null;
                
        return "/"+url;
    }
    
    private String getMethodName(String action) {
        action = action.trim();
        String[] parts = action.split("@");
        
        String methodName = parts[1];
        
        return methodName;
    }

    private Class getControllerClass(String action) throws Exception {
        action = action.trim();
        String[] parts = action.split("@");
        
        String controllerName = parts[0];
        
        try {
            return Class.forName(controllerName);
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        }
    }
}
