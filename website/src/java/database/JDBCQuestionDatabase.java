package database;

import domain.Question;
import java.util.Collection;

public class JDBCQuestionDatabase implements QuestionDatabase {
    
    private static JDBCQuestionDatabase instance;
    
    private JDBCQuestionDatabase() {
        //TODO
    }
    
    @SuppressWarnings("DoubleCheckedLocking")
    public static JDBCQuestionDatabase getInstance() {
        if (instance == null) synchronized (JDBCQuestionDatabase.class) {
            if (instance == null) instance = new JDBCQuestionDatabase();
        }
        return instance;
    }

    @Override
    public boolean add(Question question) throws DatabaseException {
        return false;
    }

    @Override
    public boolean remove(Question question) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Question question) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question get(long id) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Question> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
