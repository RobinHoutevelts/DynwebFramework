package domain;

import java.util.logging.Level;
import java.util.logging.Logger;

public class User extends Identifiable implements Removable {
    
    private static long idCounter = 0;
    
    private String name;
    private String password;
    private String email;
    private AccesLevel level;
    private boolean removed;
    
    public User(String email, String password, String name) {
        this(email, password, name, AccesLevel.USER_CREATED);
    }
    
    public User(String email, String password, String name, AccesLevel level) {
        User.idCounter++;
        
        try {
            this.setId(User.idCounter);
        } catch (DomainException exception) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, exception.getMessage(), exception);
        }
       
        User.this.setEmail(email);
        User.this.setPassword(password);
        User.this.setName(name);
        User.this.setLevel(level);
        this.removed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String username) {
        this.email = username;
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
    
    public boolean isAdmin() {return level.getLevel() >= 3;}
    public boolean isActivated() {return level.getLevel() >= 1;}
    public boolean isUser() {return level.getLevel() >= 0 && level.getLevel() <= 1;}
    public boolean isBlocked() {return level.getLevel() < 0;}

    @Override
    public void remove() {
        this.removed = true;
    }

    @Override
    public boolean isRemoved() {
        return this.removed;
    }
}
