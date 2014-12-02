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
    
    protected HashMap<Route, Resolver> Getroutes = new HashMap<Route, Resolver>();
    protected HashMap<Route, Resolver> Postroutes = new HashMap<Route, Resolver>();
    protected Container app;
    
    public Router(Container app){
        this.app =  app;
    }
    
    public void addIgnore(String string)
    {
        this.ignored.add(string);
    }
    
    public void get(String name, String url, String action) throws Exception {
        Route route = createRoute(name, url, action);
        Resolver resolver = new RouteResolver(this.app, route);
        
        this.get(route, resolver);
    }

    public void post(Route route, Resolver resolver){
        this.Postroutes.put(route, resolver);
    }
    
    public void post(String name, String url, String action) throws Exception {
        Route route = createRoute(name, url, action);
        Resolver resolver = new RouteResolver(this.app, route);
        
        this.post(route, resolver);
    }

    public void get(Route route, Resolver resolver){
        this.Getroutes.put(route, resolver);
    }
    
    public void both(String name, String url, String action) throws Exception{
        Route route = createRoute(name, url, action);
        Resolver resolver = new RouteResolver(this.app, route);
        
        this.both(route, resolver);
    }

    private Route createRoute(String name, String url, String action)
            throws Exception {
        String regex = this.regexify(url);
        
        Class controllerClass = this.getControllerClass(action);
        String methodName         = this.getMethodName(action);
        
        Route route = new Route(name, regex, controllerClass, methodName);
        return route;
    }
    
    public void both(Route route, Resolver resolver){
        this.Getroutes.put(route, resolver);
        this.Postroutes.put(route, resolver);
    }
    
    public Route getGetRouteByURL(String url)
    {
        Route route = null;
        
        for(Route r : this.Getroutes.keySet()){
            if(r.urlMatches(url)){
                route = r;
                break;
            }
        }
        
        return route;
    }
    
    public Route getGetRouteByName(String name)
    {
        Route route = null;
        
        for(Route r : this.Getroutes.keySet()){
            if(r.getName().equals(name)){
                route = r;
                break;
            }
        }
        
        return route;
    }
    
    public Route getPostRouteByURL(String url)
    {
        Route route = null;
        
        for(Route r : this.Postroutes.keySet()){
            if(r.urlMatches(url)){
                route = r;
                break;
            }
        }
        
        return route;
    }
    
    public Route getPostRouteByName(String name)
    {
        Route route = null;
        
        for(Route r : this.Postroutes.keySet()){
            if(r.getName().equals(name)){
                route = r;
                break;
            }
        }
        
        return route;
    }
    
    public Resolver getGetResolver(String url){
        Resolver resolver = null;
        Route route = this.getGetRouteByURL(url);
        if(route != null)
            resolver =this.Getroutes.get(route);
        
        return resolver;
    }
    
    public Resolver getPostResolver(String url)
    {
        Resolver resolver = null;
        Route route = this.getPostRouteByURL(url);
        if(route != null)
            resolver =this.Postroutes.get(route);
        
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

    public Resolver getResolverByUrl(String url, String requestMethod) {
        requestMethod = requestMethod.toUpperCase();
        
        Resolver resolver = null;
        if(requestMethod.equals("GET"))
            resolver = this.getGetResolver(url);
        else
            resolver = this.getPostResolver(url);
        
        return resolver;
    }
    
    public Resolver getResolverByName(String name, String requestMethod) {
        requestMethod = requestMethod.toUpperCase();
        
        Route route = null;
        Resolver resolver = null;
        if(requestMethod.equals("GET")){
            route = this.getGetRouteByURL(name);
            if(route != null)
                resolver = this.Getroutes.get(route);
        }else{
            route = this.getPostRouteByName(name);
            if(route != null)
                resolver = this.Postroutes.get(route);
        }
        
        return resolver;
    }

}
