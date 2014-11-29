package database;

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

import conf.Config;

public class Database {

    static protected Connection conn;

    public Database()
    {
        try {
            // Kijken of de driver bestaat
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Exception-handling
            e.printStackTrace();
        }
    }

    /**
     * Maakt connectie op basis van gegevens in het configuratiebestand
     * 
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException
    {
        String hostname = Config.get("dbhostname");
        String database = Config.get("dbname");
        String username = Config.get("dbusername");
        String password = Config.get("dbpassword");

        return getConnection("jdbc:mysql://"+hostname+"/"+database,username,password);
    }

    /**
     * Maakt connectie naar de databank
     * 
     * @param connectionUrl
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    static Connection getConnection(String connectionUrl,String username, String password) throws SQLException
    {
        if (conn == null || conn.isClosed())
        {
            conn = DriverManager.getConnection(connectionUrl,username,password);
        }
        return conn;
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

        // Pas als we aan de EOL zitten van de resultset stoppen we
        while(!res.isAfterLast()) {
            // Row ophalen en toevoegen aan Arraylist
            rows.add(this.getRow(res));
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
