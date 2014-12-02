package framework.html;

import java.util.HashMap;

import framework.http.router.Route;

public class Router {
    
    private framework.http.router.Router router;
    
    public Router(framework.http.router.Router router){
        this.router = router;
    }
    
    public String getUrl(String name){
        return this.getUrl(name,null);
    }
    
    public String getUrl(String name, HashMap<String, String> parameters){
        String url = "";
        Route route = this.router.getGetRouteByName(name);
        if(route == null)
            route = this.router.getPostRouteByName(name);
        
        if(route == null)
            return url;
        
        
        
        url = route.getRegex();
        url = url.substring(0, url.length()-3);
        
        if(parameters == null)
            return url;
        
        url = route.injectParameters(parameters);
        
        return url;
    }

}
