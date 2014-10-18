package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User extends Identifiable implements Removable, Serializable {
    
    private String name;
    private String password;
    private String email;
    private AccesLevel accesLevel;
    private boolean removed;
    
    protected User() {
        this(null, null, null);
    }
    
    public User(String email, String password, String name) {
        this(email, password, name, AccesLevel.USER_CREATED);
    }
    
    public User(String email, String password, String name, AccesLevel level) {
        super();
        User.this.setEmail(email);
        User.this.setPassword(password);
        User.this.setName(name);
        User.this.setAccesLevel(level);
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
        return this.accesLevel;
    }

    public void setAccesLevel(AccesLevel level) {
        this.accesLevel = level;
    }
    
    public boolean isAdmin() {return accesLevel.getLevel() >= 3;}
    public boolean isActivated() {return accesLevel.getLevel() >= 1;}
    public boolean isUser() {return accesLevel.getLevel() >= 0 && accesLevel.getLevel() <= 1;}
    public boolean isBlocked() {return accesLevel.getLevel() < 0;}

    @Override
    public void remove() {
        this.removed = true;
    }

    @Override
    public boolean isRemoved() {
        return this.removed;
    }
}
