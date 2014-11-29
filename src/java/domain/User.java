package domain;

import org.apache.commons.validator.routines.EmailValidator;


public class User extends Identifiable implements Removable {

    private String name;
    private String email;
    private AccesLevel accesLevel;
    private boolean removed;

    public User(long id, String name, String email, AccesLevel level,
            boolean removed) throws DomainException {
        this.setEmail(email);
        this.setName(name);
        this.setAccesLevel(level);
        this.setId(id);
        this.removed = removed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DomainException {
        if (name.length() <= 0)
            throw new DomainException("Naam mag niet leeg zijn.");
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) throws DomainException {
        if(!EmailValidator.getInstance().isValid(email))
            throw new DomainException("Email heeft geen correcte format.");
        this.email = email;
    }

    public AccesLevel getLevel() {
        return this.accesLevel;
    }

    public void setAccesLevel(AccesLevel level) throws DomainException {
        if(level == null)
            throw new DomainException("AccessLevel mag niet null zijn.");
        this.accesLevel = level;
    }

    public boolean isAdmin() {
        return accesLevel.getLevel() >= 3;
    }

    public boolean isActivated() {
        return accesLevel.getLevel() >= 1;
    }

    public boolean isUser() {
        return accesLevel.getLevel() >= 0 && accesLevel.getLevel() <= 1;
    }

    public boolean isBlocked() {
        return accesLevel.getLevel() < 0;
    }

    @Override
    public void remove() {
        this.removed = true;
    }

    @Override
    public boolean isRemoved() {
        return this.removed;
    }
}
