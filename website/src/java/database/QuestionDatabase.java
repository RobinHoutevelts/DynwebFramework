package database;

import domain.Question;
import java.util.Collection;

public interface QuestionDatabase {
    
    public void add(Question question) throws DatabaseException;
    
    public void remove(Question question) throws DatabaseException;
    
    public void update(Question question) throws DatabaseException;
    
    public Question get(long id) throws DatabaseException;
    
    public Collection<Question> getAll();
    
    public void closeConnection();
}