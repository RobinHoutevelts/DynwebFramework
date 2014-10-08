package controller.handlers;

import database.Database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Handler {
    
    protected Database database;
    
    public Handler(Database database) {
        this.database = database;
    }
    
    public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}