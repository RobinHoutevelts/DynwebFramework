package framework.http.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framework.Container;

public class Router {
    
    protected List<String> ignored = new ArrayList<String>();
    
    protected HashMap<String, Resolver> Getroutes = new HashMap<String, Resolver>();
    protected HashMap<String, Resolver> Postroutes = new HashMap<String, Resolver>();
    protected Container app;
    
    public Router(Container app){
        this.app =  app;
    }
    
    public void addIgnore(String string)
    {
        this.ignored.add(string);
    }
    
    public void get(String url, String action) throws Exception{
        List<String> parameters = this.getParameters(url);
        String regex = this.regexify(url);
        
        Resolver resolver = this.createResolver(regex, action, parameters);
        
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Route '"+url+"' gemaakt.");
        
        this.get(regex, resolver);
    }
    
    public void get(String regex, Resolver resolver){
        
        this.Getroutes.put(regex, resolver);
    }
    
    public void post(String url, String action) throws Exception{
        List<String> parameters = this.getParameters(url);
        String regex = this.regexify(url);
        
        Resolver resolver = this.createResolver(regex, action, parameters);
        this.post(regex, resolver);
    }
    
    public void post(String regex, Resolver resolver){
        this.Postroutes.put(regex, resolver);
    }
    
    public void both(String url, String action) throws Exception{
        List<String> parameters = this.getParameters(url);
        String regex = this.regexify(url);
        
        Resolver resolver = this.createResolver(regex, action, parameters);
        
        this.both(regex, resolver);
    }
    
    public void both(String regex, Resolver resolver){
        this.Getroutes.put(regex, resolver);
        this.Postroutes.put(regex, resolver);
    }
    
    public Resolver getGetResolver(String url){
        return this.getResolver(this.Getroutes, url);
    }
    
    public Resolver getPostResolver(String url)
    {
        return this.getResolver(this.Postroutes, url);
    }
    
    public Resolver getResolver(String url, String requestMethod){
        requestMethod = requestMethod.toUpperCase();
        
        if(requestMethod == "POST")
            return this.getPostResolver(url);
        else
            return this.getGetResolver(url);
    }
    
    private Resolver getResolver(HashMap<String, Resolver> routes,String url){
        Resolver resolver = null;
        
        url = this.trimUrl(url);
        
        System.out.println(url);
        
        for(String regex : routes.keySet()){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(url);
            
            if(!matcher.matches())
                continue;
            
            resolver = routes.get(regex);
        }
        
        return resolver;
    }
    
    private Resolver createResolver(String regex, String action, List<String> parameters) throws Exception{
        action = action.trim();
        String[] parts = action.split("@");
        
        String controllerName = parts[0];
        String methodName = parts[1];

        return new RouteResolver(app, regex, Class.forName(controllerName), methodName, parameters);
    }
    
    private List<String> getParameters(String url) throws Exception
    {
        ArrayList<String> parameters = new ArrayList<String>();
        url = this.trimUrl(url);
        Pattern pattern = Pattern.compile(":(\\w+)");
        Matcher matcher = pattern.matcher(url);
        
        int groups = matcher.groupCount();
        
        groups--;

        while(matcher.find()){
            String parameter = matcher.group(1);
            parameters.add(parameter);
        }
        
        if(parameters.size() != groups)
            throw new Exception("Router failed to extract parameters");
        
        return parameters;
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

}
