package controller;

import controller.handlers.AjaxHandlerFactory;
import controller.handlers.RequestHandlerFactory;

import database.DatabaseException;

import domain.AccesLevel;
import domain.User;

import service.ServiceException;
import service.WebService;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    
    private WebService webService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        webService = new WebService();
        ServletContext context = sce.getServletContext();
        context.setAttribute("requestHandlerFactory", new RequestHandlerFactory(webService));
        context.setAttribute("ajaxHandlerFactory", new AjaxHandlerFactory(webService));
        
        User adminTimLenaers = new User("tim.lenaers@student.khleuven.be", "passwordHash", "Tim Lenaers", AccesLevel.ADMIN);
        User adminFeryVousure = new User("fery.vousure@student.khleuven.be", "passwordHash", "Fery Vousure", AccesLevel.ADMIN);
        User adminKevinPeeters = new User("kevin.peeters3@student.khleuven.be", "passwordHash", "Kevin Peeters", AccesLevel.ADMIN);
        User adminRubenMoermans = new User("ruben.moermans@student.khleuven.be", "passwordHash", "Ruben Moermans", AccesLevel.ADMIN);
        User adminJasperDeValck = new User("jasper.de.valck@student.khleuven.be", "passwordHash", "Jasper De Valck", AccesLevel.ADMIN);
        User adminRobinWoodfields = new User("robin.woodfields@student.khleuven.be", "passwordHash", "Robin Woodfields", AccesLevel.ADMIN);
        User adminMatthiasDesard = new User("matthias.jan.desard@student.khleuven.be", "passwordHash", "Matthias Desard", AccesLevel.ADMIN);
        User adminBartVanLooveren = new User("bart.van.looveren@student.khleuven.be", "passwordHash", "Bart van Looveren", AccesLevel.ADMIN);
        
        try {
            this.webService.addUser(adminTimLenaers);
            this.webService.addUser(adminFeryVousure);
            this.webService.addUser(adminKevinPeeters);
            this.webService.addUser(adminRubenMoermans);
            this.webService.addUser(adminJasperDeValck);
            this.webService.addUser(adminRobinWoodfields);
            this.webService.addUser(adminMatthiasDesard);
            this.webService.addUser(adminBartVanLooveren);
        } catch (DatabaseException exception) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, exception.getMessage(), exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            webService.close();
        } catch (ServiceException exception) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, "WebService was not closed correctly!", exception);
        }
    }
}