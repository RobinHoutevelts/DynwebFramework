package framework.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import framework.http.router.Route;

public class Router {
    
    private framework.http.router.Router router;
    
    public Router(framework.http.router.Router router){
        this.router = router;
    }
    
    public String getUrl(String name){
        return this.getUrl(name,null);
    }
    
    public String getUrl(String name, String parameters){
        
        String url = "";
        
        // goh, ik ga ervanuit dat je niet een naam gaat gebruiken voor
        // verschillende urls..
        
        // bvb GET  users.create /users/create
        // en  POST users.create /users/store
        // da zou ni lukken
        
        Route route = this.router.getRouteByName(name,"get");
        if(route == null)
            route = this.router.getRouteByName(name,"post");
                
        if(route == null)
            return url;
        
        url = route.getRegex();
        
        if(parameters != null){
            parameters = parameters.trim();
            List<String> params = new ArrayList<String>(Arrays.asList(parameters.split(",")));
            
            url = route.injectParameters(params);
        }
        
        url = url.substring(0, url.length()-3);
        
        return url;
    }

}
