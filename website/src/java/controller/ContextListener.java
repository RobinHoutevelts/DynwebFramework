package controller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import conf.Config;
import database.DatabaseException;
import database.UserDatabase;
import domain.AccesLevel;
import domain.User;
import framework.Container;
import framework.event.Emitter;
import framework.event.Event;
import framework.service.providers.DatabaseServiceProvider;
import framework.service.providers.EventServiceProvider;
import framework.service.providers.RequestHandlersServiceProvider;
import framework.service.providers.ServiceProvider;

public class ContextListener implements ServletContextListener {

    private Container app;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* Bootstrap Application */

        // Laad configuratiebestand
        Config.load();

        this.app = new Container();

        // TODO: generate list of ServiceProviders
        ArrayList<ServiceProvider> providers = new ArrayList<ServiceProvider>();

        providers.add(new EventServiceProvider());
        providers.add(new DatabaseServiceProvider());
        providers.add(new RequestHandlersServiceProvider());
        

        // Register serviceproviders
        for (ServiceProvider provider : providers) {
            provider.register(app);
        }

        ServletContext context = sce.getServletContext();
        context.setAttribute("app", this.app);

        UserDatabase userDatabase = (UserDatabase) this.app.make("UserDatabase");

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
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE,exception.getMessage(), exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Emitter eventEmitter = (Emitter) this.app.make("EventEmitter");
            eventEmitter.fire(new Event("contextDestroyed"));
        } catch (Exception e) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE,"WebService was not closed correctly!", e);
        }
    }
}