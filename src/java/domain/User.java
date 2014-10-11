package domain;

public class User {
    
    private String username;
    private String password;
    private AccesLevel level;
    
    public User(String username, String password) {
        this(username, password, AccesLevel.USER);
    }
    
    public User(String username, String password, AccesLevel level) {
        User.this.setUsername(username);
        User.this.setPassword(password);
        User.this.setLevel(level);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccesLevel getLevel() {
        return level;
    }

    public void setLevel(AccesLevel level) {
        this.level = level;
    }
}
