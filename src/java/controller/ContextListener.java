package controller;

import controller.handlers.AjaxHandlerFactory;
import controller.handlers.RequestHandlerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import service.WebService;

public class ContextListener implements ServletContextListener {
    
    private WebService webService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        webService = new WebService();
        ServletContext context = sce.getServletContext();
        context.setAttribute("requestHandlerFactory", new RequestHandlerFactory(webService));
        context.setAttribute("ajaxHandlerFactory", new AjaxHandlerFactory(webService));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        webService.close();
    }
}