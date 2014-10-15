package controller.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.WebService;

public abstract class Handler {
    
    protected WebService webService;
    
    public Handler(WebService webService) {
        this.webService = webService;
    }
    
    public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}