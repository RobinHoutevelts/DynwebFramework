package database;

import domain.User;
import java.util.Collection;

public interface UserDatabase {

    public boolean add(User user) throws DatabaseException;

    public boolean remove(User user) throws DatabaseException;

    public boolean update(User user) throws DatabaseException;

    public User get(long id) throws DatabaseException;

    public Collection<User> getAll();
}