package database;

import domain.User;
import java.util.Collection;
import java.util.HashMap;
import java.sql.SQLException;

public class JDBCUserDatabase implements UserDatabase {

    protected Database db;

    public JDBCUserDatabase(Database database) {
        //TODO
        this.db = database;
    }
    @Override
    public boolean add(User user) throws DatabaseException {
        String sql = "INSERT INTO Users (name,email,removed,password,level)"
                + "VALUES(:name ,:email ,:removed ,:password ,:level )";

        int id = 0;
        NamedParamStatement stmt;
        try {
            stmt = this.db.namedParamStatement(sql);

            stmt.setString("name", user.getName());
            stmt.setString("email", user.getEmail());
            stmt.setBoolean("removed", user.isRemoved());
            stmt.setString("password", user.getPassword());
            stmt.setInt("level", user.getLevel().ordinal());

            id = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        if (id > 0){
            return true;
        }else{
            return false;
        }
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
}
