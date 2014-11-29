package database;

import domain.Question;
import domain.User;

import java.util.Collection;
import java.util.List;

public interface QuestionDatabase {
    
    public Question add(User user, String text) throws DatabaseException;

    public Question add(User user, String text, boolean approved,
            boolean reviewed, boolean removed) throws DatabaseException;
    
    public List<Question> getByUser(User user) throws DatabaseException;
    
    public List<Question> getByUser(User user, String text) throws DatabaseException;

    public void remove(Question question) throws DatabaseException;

    public void update(Question question) throws DatabaseException;

    public Question get(long id) throws DatabaseException;

    public Collection<Question> getAll();
}