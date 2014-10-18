package service;

import database.DatabaseException;
import database.JDBCQuestionDatabase;
import database.JDBCUserDatabase;
import database.QuestionDatabase;
import database.UserDatabase;
import domain.Question;
import domain.User;
import java.util.Collection;

public class WebService {
    
    private final QuestionDatabase questionDatabase;
    private final UserDatabase userDatabase;
    
    public WebService() {
        questionDatabase = JDBCQuestionDatabase.getInstance();
        userDatabase = JDBCUserDatabase.getInstance();
    }
    
    public boolean addQuestion(Question question) throws DatabaseException {
        return questionDatabase.add(question);
    }
    
    public boolean updateQuestion(Question question) throws DatabaseException {
        return questionDatabase.update(question);
    }
    
    public boolean removeQuestion(Question question) throws DatabaseException {
        return questionDatabase.remove(question);
    }
    
    public boolean removeQuestion(long id) throws DatabaseException {
        Question removeQuestion = questionDatabase.get(id);
        return this.removeQuestion(removeQuestion);
    }
    
    public Question getQuestion(long id) throws DatabaseException {
        return questionDatabase.get(id);
    }
    
    public Collection<Question> getAllQuestions() {
        return questionDatabase.getAll();
    }
    
    public boolean addUser(User user) throws DatabaseException {
        return userDatabase.add(user);
    }
    
    public boolean updateUser(User user) throws DatabaseException {
        return userDatabase.update(user);
    }
    
    public boolean removeUser(User user) throws DatabaseException {
        return userDatabase.remove(user);
    }
    
    public boolean removeUser(long id) throws DatabaseException {
        User removeUser = userDatabase.get(id);
        return this.removeUser(removeUser);
    }
    
    public User getUser(long id) throws DatabaseException {
        return userDatabase.get(id);
    }
    
    public Collection<User> getAllUsers() {
        return userDatabase.getAll();
    }
    
    public void close() throws ServiceException {
        questionDatabase.closeConnection();
        userDatabase.closeConnection();
    }
}
