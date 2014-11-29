package framework.service.providers;

import database.Database;
import database.JDBCQuestionDatabase;
import database.JDBCUserDatabase;
import database.QuestionDatabase;
import database.UserDatabase;
import framework.Container;
import framework.service.Resolver;

/**
 * Database ServiceProvider van HotProgrammers.net
 *
 */
public class DatabaseServiceProvider implements ServiceProvider {

    public void register(Container app) {
        // Aanmaken Database
        // TODO: config uitlezen
        Database db = new Database();
        
        // Aanmaken Repositories
        UserDatabase userDb = new JDBCUserDatabase(db);
        QuestionDatabase questionDb = new JDBCQuestionDatabase(db);

        // Objecten toevoegen aan de IoC-container
        app.bind("Database", new Resolver() {

            @Override
            public Object resolve() {
                return db;
            }
        });

        app.bind("UserDatabase", new Resolver() {
            @Override
            public Object resolve() {
                return userDb;
            }
        });

        app.bind("QuestionDatabase", new Resolver() {

            @Override
            public Object resolve() {
                return questionDb;
            }
        });
    }

}
