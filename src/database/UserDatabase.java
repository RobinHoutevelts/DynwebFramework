package database;

import domain.AccesLevel;
import domain.User;

import java.util.Collection;

public interface UserDatabase {

    public User add(String name, String email, String password, AccesLevel level)
            throws DatabaseException;
    
    public boolean remove(User user) throws DatabaseException;

    public boolean update(User user) throws DatabaseException;

    public User get(long id) throws DatabaseException;
    
    public User getByEmail(String email) throws DatabaseException;

    public User getByCredentials(String email, String password) throws DatabaseException;
    
    public Collection<User> getAll();


}