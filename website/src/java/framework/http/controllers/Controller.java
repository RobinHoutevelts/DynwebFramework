package framework.http.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;

import framework.Container;
import framework.http.Request;
import framework.http.Response;

public class Controller {
    
    protected Container app;
    
    protected Request request;
    protected Response response;
    protected String viewDir;
        
    public Controller(Container app){
        this.app = app;
        this.viewDir = "/view/";
    }
    
    public void setRequest(Request request){
        this.request = request;
    }
    
    public void setResponse(Response response){
        this.response = response;
    }
    
    protected void dispatch(String view) throws ServletException{
        view = this.getViewLocation(view);
        
        try {
            this.request.getRequestDispatcher(view).forward(this.request, this.response);
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }
    
    private String getViewLocation(String view){
        String tmp = view;
        do{
            view = tmp;
            tmp = view.replaceAll("^/|/$", "");
        }while(!view.equals(tmp));
        tmp = null;
        
        return this.viewDir+view;
    }

}
