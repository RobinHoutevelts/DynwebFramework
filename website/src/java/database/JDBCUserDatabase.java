package database;

import domain.User;
import java.util.Collection;

public class JDBCUserDatabase implements UserDatabase {
    
    private static JDBCUserDatabase instance;
    
    private JDBCUserDatabase() {
        //TODO
    }
    
    @SuppressWarnings("DoubleCheckedLocking")
    public static JDBCUserDatabase getInstance() {
        if (instance == null) synchronized (JDBCUserDatabase.class) {
            if (instance == null) instance = new JDBCUserDatabase();
        }
        return instance;
    }

    @Override
    public boolean add(User user) throws DatabaseException {
        return false;
    }

    @Override
    public boolean remove(User user) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(User user) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User get(long id) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
