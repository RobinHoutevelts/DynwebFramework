package framework.listener;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import framework.Bootstrapper;
import framework.Container;
import framework.event.Bag;
import framework.event.Emitter;
import framework.event.Event;
import framework.event.EventEmitter;

public class ContextListener implements ServletContextListener {

    private Container app;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* Bootstrap Application */
        this.app = new Container();
        
        Bootstrapper bootstrapper = new Bootstrapper(app);
        bootstrapper.initialize();

        ServletContext context = sce.getServletContext();
        context.setAttribute("app", this.app);

        EventEmitter emitter = (EventEmitter) this.app.make("EventEmitter");
        
        Bag eventBag = new Bag();
            eventBag.setAttribute("context", context);
        
        emitter.fire(new Event("context.initialized", eventBag));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Emitter eventEmitter = (Emitter) this.app.make("EventEmitter");
            eventEmitter.fire(new Event("context.destroyed"));
        } catch (Exception e) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE,"WebService was not closed correctly!", e);
        }
    }
}