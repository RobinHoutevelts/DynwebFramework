package framework.service.providers;

import framework.Container;
import framework.config.Config;
import framework.database.Database;
import framework.event.Emitter;
import framework.event.Event;
import framework.event.EventHandler;
import framework.service.Resolver;

/**
 * Database ServiceProvider van HotProgrammers.net
 *
 */
public class DatabaseServiceProvider implements ServiceProvider {

    public void register(Container app) {
        // Aanmaken Database
        
        String dbDriver = Config.get("dbDriver");
        String dbConnectionUrl = Config.get("dbConnectionUrl");
        String dbUsername = Config.get("dbUsername");
        String dbPassword = Config.get("dbPassword");
        
        if(dbDriver == null || dbConnectionUrl == null || dbUsername == null || dbPassword == null)
            return;
        
        Database db = new Database(dbDriver,dbConnectionUrl,dbUsername,dbPassword);

        // Objecten toevoegen aan de IoC-container
        app.bind("Database", new Resolver() {

            @Override
            public Object resolve() {
                return db;
            }
        });
        
        // Databaseverbinding sluiten
        Emitter eventEmitter = (Emitter) app.make("EventEmitter");
        eventEmitter.listen("context.destroyed", new EventHandler() {
            
            @Override
            public void handle(Event event) {
                db.closeConnection();
            }
        });
    }

}
