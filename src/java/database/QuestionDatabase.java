package database;

import domain.Question;
import domain.User;

import java.util.Collection;

public interface QuestionDatabase {

    public Question add(User user, String text, boolean approved,
            boolean reviewed, boolean removed) throws DatabaseException;

    public void remove(Question question) throws DatabaseException;

    public void update(Question question) throws DatabaseException;

    public Question get(long id) throws DatabaseException;

    public Collection<Question> getAll();
}