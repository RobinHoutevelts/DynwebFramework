package service;

import database.QuestionDatabase;
import database.UserDatabase;
import domain.Question;
import domain.User;
import java.util.Collection;

public class WebService {
    
    private QuestionDatabase questionDatabase;
    private UserDatabase userDatabase;
    
    public WebService() {
        //TODO
    }
    
    public boolean addQuestion(Question question) throws ServiceException {
        return false;
    }
    
    public boolean updateQuestion(Question question) throws ServiceException {
        return false;
    }
    
    public boolean removeQuestion(Question question) throws ServiceException {
        return false;
    }
    
    public boolean removeQuestion(long id) throws ServiceException {
        return false;
    }
    
    public User getQuestion(long id) throws ServiceException {
        return null;
    }
    
    public Collection<Question> getAllQuestions() {
        return null;
    }
    
    public boolean addUser(User user) throws ServiceException {
        return false;
    }
    
    public boolean updateUser(User user) throws ServiceException {
        return false;
    }
    
    public boolean removeUser(User user) throws ServiceException {
        return false;
    }
    
    public boolean removeUser(long id) throws ServiceException {
        return false;
    }
    
    public User getUser(long id) throws ServiceException {
        return null;
    }
    
    public Collection<User> getAllUsers() {
        return null;
    }
    
    public void close() {
        //TODO
    }
}
