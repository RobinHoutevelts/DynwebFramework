package framework.http;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class Request extends HttpServletRequestWrapper {
    
    private HashMap<String, String> params  = new HashMap<String, String>();

    public Request(HttpServletRequest request) {
        super(request);
    }
    
    public String getParameter(String name){
        if(this.params.containsKey(name))
            return this.params.get(name);
        
        return super.getParameter(name);
    }
    
    public void addParameter(String name, String value){
        this.params.put(name, value);
    }

}
