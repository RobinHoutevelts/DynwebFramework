package service.providers;

import service.IoC;
import service.IoCResolver;
import database.Database;
import database.JDBCQuestionDatabase;
import database.JDBCUserDatabase;
import database.QuestionDatabase;
import database.UserDatabase;

/**
 * Database ServiceProvider van HotProgrammers.net
 *
 */
public class DatabaseServiceProvider implements ServiceProvider {

    public void register(IoC app) {
        // Aanmaken Database
        // TODO: config uitlezen
        Database db = new Database();
        
        // Aanmaken Repositories
        UserDatabase userDb = new JDBCUserDatabase(db);
        QuestionDatabase questionDb = new JDBCQuestionDatabase(db);

        // Objecten toevoegen aan de IoC-container
        app.bind("Database", new IoCResolver() {

            @Override
            public Object resolve() {
                return db;
            }
        });

        app.bind("UserDatabase", new IoCResolver() {
            @Override
            public Object resolve() {
                return userDb;
            }
        });

        app.bind("QuestionDatabase", new IoCResolver() {

            @Override
            public Object resolve() {
                return questionDb;
            }
        });
    }

}
