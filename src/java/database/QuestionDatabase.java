package database;

import domain.Question;
import java.util.Collection;

public interface QuestionDatabase {
    
    public boolean add(Question question) throws DatabaseException;
    
    public boolean remove(Question question) throws DatabaseException;
    
    public boolean update(Question question) throws DatabaseException;
    
    public Question get(long id) throws DatabaseException;
    
    public Collection<Question> getAll();
    
    public void closeConnection();
}