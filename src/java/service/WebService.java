package service;

import database.Database;
import database.JDBCQuestionDatabase;
import database.JDBCUserDatabase;
import database.QuestionDatabase;
import database.UserDatabase;

public class WebService {

    private Database database;
    private final QuestionDatabase questionDatabase;
    private final UserDatabase userDatabase;

    /**
     * Webservice als simpele Service Locator
     */
    public WebService() {
        database = new Database();
        questionDatabase = new JDBCQuestionDatabase(database);
        userDatabase = new JDBCUserDatabase(database);
    }

    public QuestionDatabase getQuestionDatabase()
    {
        return this.questionDatabase;
    }

    public UserDatabase getUserDatabase()
    {
        return this.userDatabase;
    }

    public void close() throws ServiceException {
        database.closeConnection();
    }
}
