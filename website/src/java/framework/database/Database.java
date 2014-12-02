package framework.database;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

public class Database {

    protected Connection conn;
    protected String connectionUrl;
    protected String username;
    protected String password; // TODO: kijken in hoeverre het haalbaar is het wachtwoord hier weg te laten

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Database(String driver, String connectionUrl, String username, String password)
    {
        try {
            // Kijken of de driver bestaat
            Class DriverClass = Class.forName(driver);
            
            Constructor constructorClass = DriverClass.getConstructor();
            Driver dbDriver = (Driver) constructorClass.newInstance();
            DriverManager.registerDriver(dbDriver);
            
        } catch (Exception e) {
            // TODO Exception-handling
            e.printStackTrace();
        }
        
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
    }

    /**
     * Maakt connectie naar de databank
     * 
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException
    {
        if(this.conn == null || this.conn.isClosed())
            this.conn = DriverManager.getConnection(connectionUrl,username,password);
        
        return this.conn;
    }

    public PreparedStatement prepareStatement(String query) throws SQLException
    {
        return conn.prepareStatement(query);
    }

    public NamedParamStatement namedParamStatement(String query) throws SQLException
    {
        return new NamedParamStatement(getConnection(), query);
    }

    /**
     * Geeft alle rows van een result in een handige ArrayList
     * 
     * @param res
     * @return
     * @throws SQLException
     */
    public ArrayList<DatabaseRow> getAllRows(ResultSet res) throws SQLException
    {
        ArrayList<DatabaseRow> rows = new ArrayList<DatabaseRow>();

        // Cursor naar -1 zetten.
        res.beforeFirst();
                
        // Kijken of er op index 0 iets zit.
        if(res.next()){
            // Blijkbaar zijn er rows
            // Cursor terug naar -1 zetten alle rows aflopen.
            res.beforeFirst();
            
            // Pas als we aan de EOL zitten van de resultset stoppen we
            while(!res.isAfterLast()) {
                // Row ophalen en toevoegen aan Arraylist
                DatabaseRow row = this.getRow(res);
                if(row != null)
                    rows.add(row);
            }
        }

        res.close();

        return rows;
    }

    /**
     * Haalt de volgende row uit een resultset een geeft dit terug in een Hashmap<Kolomnaam,Object>
     * 
     * @param res
     * @return
     * @throws SQLException
     */
    public DatabaseRow getRow(ResultSet res) throws SQLException
    {
        HashMap<String, Object> row = new HashMap<String, Object>();

        // MetaData is nodig, daarin zit het aantal kolommen en hoe ze heten
        ResultSetMetaData metaData = res.getMetaData();
        int columnCount = metaData.getColumnCount();

        ArrayList<String> columns = new ArrayList<String>();

        // Alle kolommen aflopen en hun naam bijhouden in de Array.
        for (int columnNr=1; columnNr<=columnCount; columnNr++){
            String columnName = metaData.getColumnName(columnNr);
            columns.add(columnName);
        }

        // Springen naar de volgende row
        if (res.next()){
            // Alle kolommen in deze row aflopen
            for (String columnName:columns) {
                // De waarde van deze kolom ophalen
                Object rowData = res.getObject(columnName);
                // Toevoegen aan de hashmap
                row.put(columnName,rowData);
            }
        }
        
        if(row.size() <= 0)
            return null;

        return new DatabaseRow(row);
    }
    
    public void closeConnection() {
        try {
            conn.close();
            
            // This manually deregisters JDBC driver, which prevents Tomcat 7
            // from complaining about memory leaks wrto this class
            // http://stackoverflow.com/a/5315467/1306509
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }

        } catch (SQLException e) {
            // TODO Exception-handling
            e.printStackTrace();
        }
    }
}
