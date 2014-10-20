package database;

import domain.AccesLevel;
import domain.DomainException;
import domain.User;

import java.util.Collection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUserDatabase implements UserDatabase {

    protected Database db;

    public JDBCUserDatabase(Database database) {
        //TODO
        this.db = database;
    }
    
    @Override
    public User add(String name,String email, String password, AccesLevel level) throws DatabaseException {
        User user = null;
        
        String sql = "INSERT INTO Users (name,email,removed,password,level)"
                + "VALUES(:name ,:email,:removed,:password,:level)";
        
        int rowsAffected = 0;
        try {
            // Prepared Statement maken
            NamedParamStatement stmt = this.db.namedParamStatement(sql);

            // Prepared Statement vullen
            stmt.setString("name", name);
            stmt.setString("email", email);
            stmt.setBoolean("removed", false);
            stmt.setString("password", password);
            stmt.setInt("level", level.ordinal());

            // Prepared Statement uitvoeren
            rowsAffected = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        
        if(rowsAffected < 0) {
            // Failed to insert!
            // TODO: Error handling
            throw new DatabaseException("Kon gebruiker niet toevoegen.");
        }else{
            // Zonet gemaakte gebruiker ophalen vanuit DB
            user = this.getByEmail(email);
        }
        
        return user;
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
    public User getByEmail(String email) throws DatabaseException
    {
        User user = null;
        
        String sql = "SELECT id,name,email,removed,password,level FROM Users "
                + "WHERE email = :email";
        
        try{
            // Prepared Statement maken
            NamedParamStatement stmt = this.db.namedParamStatement(sql);
            
            // Prepared Statement vullen
            stmt.setString("email", email);
            
            // Prepared Statement uitvoeren
            ResultSet res = stmt.executeQuery();
            
            // Gegevens ophalen
            DatabaseRow row = this.db.getRow(res);
            
            stmt.close();
            
            if (row == null) {
                throw new DatabaseException("Kon gebruiker met email '"+email+"' niet vinden.");
            }else{
                // Gebruikerobject aanmaken
                
                long id = row.getLong("id");
                email = row.getString("email");
                String name = row.getString("name");
                
                AccesLevel level = AccesLevel.values()[row.getInt("level")];
   
                user = new User(id,email,name,level);
            }
            
        }catch(SQLException|DomainException e){
            throw new DatabaseException(e);
        }
        
        return user;
    }
    
    @Override
    public User getByCredentials(String email,String password) throws DatabaseException
    {
        User user = null;
        String sql = "SELECT id,name,email,removed,password,level FROM Users "
                + "WHERE email = :email "
                + "AND   password = :password";
        
        try{
            // Prepared Statement maken
            NamedParamStatement stmt = this.db.namedParamStatement(sql);
            
            // Prepared Statement vullen
            stmt.setString("email", email);
            stmt.setString("password", password);
            
            // Prepared Statement uitvoeren
            ResultSet res = stmt.executeQuery();
            
            // Gegevens ophalen
            DatabaseRow row = this.db.getRow(res);
            
            stmt.close();
            
            if (row == null) {
                throw new DatabaseException("Kon gebruiker met email '"+email+"' en dat wachtwoord niet vinden.");
            }else{
                // Gebruikerobject aanmaken
                
                long id = row.getLong("id");
                email = row.getString("email");
                String name = row.getString("name");
                
                AccesLevel level = AccesLevel.values()[row.getInt("level")];
   
                user = new User(id,email,name,level);
            }
            
        }catch(SQLException|DomainException e){
            throw new DatabaseException(e);
        }
        
        return user;
    }

    @Override
    public Collection<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}