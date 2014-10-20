package controller;

import conf.Config;
import controller.handlers.AjaxHandlerFactory;
import controller.handlers.RequestHandlerFactory;

import database.DatabaseException;

import database.UserDatabase;
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
        // Laad configuratiebestand
        Config.load();
        webService = new WebService();
        ServletContext context = sce.getServletContext();
        context.setAttribute("requestHandlerFactory", new RequestHandlerFactory(webService));
        context.setAttribute("ajaxHandlerFactory", new AjaxHandlerFactory(webService));

        UserDatabase userDatabase = this.webService.getUserDatabase();
   
        try {
            User adminTimLenaers = userDatabase.add("Tim Lenaers", "tim.lenaers@student.khleuven.be", "passwordHash", AccesLevel.ADMIN);
            User adminFeryVousure = userDatabase.add("Fery Vousure", "fery.vousure@student.khleuven.be", "passwordHash", AccesLevel.ADMIN);
            User adminKevinPeeters = userDatabase.add("Kevin Peeters", "kevin.peeters3@student.khleuven.be", "passwordHash", AccesLevel.ADMIN);
            User adminRubenMoermans = userDatabase.add("Ruben Moermans", "ruben.moermans@student.khleuven.be", "passwordHash", AccesLevel.ADMIN);
            User adminJasperDeValck = userDatabase.add("Jasper De Valck", "jasper.de.valck@student.khleuven.be", "passwordHash", AccesLevel.ADMIN);
            User adminRobinWoodfields = userDatabase.add("Robin Houtevelts", "robin.houtevelts@student.khleuven.be", "passwordHash", AccesLevel.ADMIN);
            User adminMatthiasDesard = userDatabase.add("Matthias Desard", "matthias.jan.desard@student.khleuven.be", "passwordHash", AccesLevel.ADMIN);
            User adminBartVanLooveren = userDatabase.add("Bart van Looveren", "bart.van.looveren@student.khleuven.be", "passwordHash", AccesLevel.ADMIN);
            
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