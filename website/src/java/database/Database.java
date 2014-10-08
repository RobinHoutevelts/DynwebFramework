package database;

//import java.util.Collection;

public interface Database {
    
    //public void add(Identifiable identifiable) throws DatabaseException;
    
    //public void remove(Identifiable identifiable) throws DatabaseException;
    
    //public void update(Identifiable identifiable) throws DatabaseException;
    
    //public Identifiable get(long id, Class classType) throws DatabaseException;
    
    //public Collection<Identifiable> getAll(Identifiable dummyObject);
    
    public void closeConnection();
}