package framework.database;

import java.util.HashMap;

public class DatabaseRow {
    
    private HashMap<String, Object> row;
    
    public DatabaseRow(HashMap<String, Object> row)
    {
        this.row = row;
    }
    
    public int getInt(String columnName)
    {
        return (int) row.get(columnName);
    }
    
    public String getString(String columnName)
    {
        return (String) row.get(columnName);
    }
    
    public long getLong(String columnName)
    {
        return (long) row.get(columnName);
    }
    
    public boolean getBoolean(String columnName)
    {
        return (boolean) row.get(columnName);
    }

}
