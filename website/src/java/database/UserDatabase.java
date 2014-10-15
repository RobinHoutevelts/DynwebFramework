package database;

import domain.User;
import java.util.Collection;

public interface UserDatabase {
    
    public void add(User user) throws DatabaseException;
    
    public void remove(User user) throws DatabaseException;
    
    public void update(User user) throws DatabaseException;
    
    public User get(long id) throws DatabaseException;
    
    public Collection<User> getAll();
    
    public void closeConnection();
}