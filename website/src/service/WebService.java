package service;

import database.Database;
import database.JDBCQuestionDatabase;
import database.JDBCUserDatabase;
import database.QuestionDatabase;
import database.UserDatabase;

public class WebService {

    private final QuestionDatabase questionDatabase;
    private final UserDatabase userDatabase;

    public WebService() {
        Database database = new Database();
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
        questionDatabase.closeConnection();
        userDatabase.closeConnection();
    }
}
