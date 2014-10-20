package database;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

//http://stackoverflow.com/a/20644736/1306509
public class NamedParamStatement {
    public NamedParamStatement(Connection conn, String sql) throws SQLException {
        int pos;
        while((pos = sql.indexOf(":")) != -1) {
            int end = sql.substring(pos).indexOf(" ");
            if (end == -1)
                end = sql.length();
            else
                end += pos;
            fields.add(sql.substring(pos+1,end));
            sql = sql.substring(0, pos) + "?" + sql.substring(end);
        }       
        prepStmt = conn.prepareStatement(sql);
    }

    public PreparedStatement getPreparedStatement() {
        return prepStmt;
    }
    public ResultSet executeQuery() throws SQLException {
        return prepStmt.executeQuery();
    }
    public int executeUpdate() throws SQLException {
        return prepStmt.executeUpdate();
    }
    public void close() throws SQLException {
        prepStmt.close();
    }

    public void setInt(String name, int value) throws SQLException {

        prepStmt.setInt(getIndex(name), value);
    }

    public void setLong(String name, long value) throws SQLException {       
        prepStmt.setLong(getIndex(name), value);
    }

    public void setString(String name, String value) throws SQLException {
        prepStmt.setString(getIndex(name), value);
    }

    public void setArray(String name, Array value) throws SQLException {      
        prepStmt.setArray(getIndex(name), value);

    }

    public void setBoolean(String name, boolean value) throws SQLException {      
        prepStmt.setBoolean(getIndex(name), value);

    }

    public void setDate(String name, Date value) throws SQLException {      
        prepStmt.setDate(getIndex(name), value);

    }

    public void setFloat(String name, float value) throws SQLException {      
        prepStmt.setFloat(getIndex(name), value);

    }

    public void setTime(String name, Time value) throws SQLException {      
        prepStmt.setTime(getIndex(name), value);
    }

    private int getIndex(String name) {
        return fields.indexOf(name)+1;
    }
    private PreparedStatement prepStmt;
    private List<String> fields = new ArrayList<String>();
}
