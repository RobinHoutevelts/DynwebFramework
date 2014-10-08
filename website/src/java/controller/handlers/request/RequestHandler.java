package controller.handlers.request;

import controller.handlers.Handler;
import database.Database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHandler extends Handler {
    
    private String view;
    
    public RequestHandler(Database database, String view) {
        super(database);
        this.view = view;
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return this.getView();
    }
    
    public String getView() {
        return this.view;
    }
    
    protected void setView(String view) {
        this.view = view;
    }
}