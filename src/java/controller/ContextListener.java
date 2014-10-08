package controller;

import controller.handlers.AjaxHandlerFactory;
import controller.handlers.RequestHandlerFactory;

import database.Database;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    
    private Database database;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        database = null;
        ServletContext context = sce.getServletContext();
        context.setAttribute("requestHandlerFactory", new RequestHandlerFactory(database));
        context.setAttribute("ajaxHandlerFactory", new AjaxHandlerFactory(database));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        database.closeConnection();
    }
}