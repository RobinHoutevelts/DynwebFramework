package framework.http;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class Request extends HttpServletRequestWrapper {
    
    private HashMap<String, String> params  = new HashMap<String, String>();
    private Session session;

    public Request(HttpServletRequest request) {
        super(request);
    }
    
    public boolean isAjax(){
        String header = this.getHeader("X-Requested-With");
        if(header == null)
            return false;
        
        return header.equals("XMLHttpRequest");
    }
    
    public String getParameter(String name){
        if(this.params.containsKey(name))
            return this.params.get(name);
        
        return super.getParameter(name);
    }
    
    public void addParameter(String name, String value){
        this.params.put(name, value);
    }
    
    public void setSession(Session session){
        this.session = session;
    }
    
    public Session getSession(){
        return this.session;
    }
    
    public Session getSession(boolean create){
        return this.session;
    }

}
