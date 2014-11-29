package domain.commands.user;

import org.apache.commons.validator.routines.EmailValidator;

import database.DatabaseException;
import database.UserDatabase;
import domain.AccesLevel;
import domain.DomainException;
import domain.User;

public class CreateUserCommand {

    private UserDatabase db;

    private String name;
    private String email;
    private String wachtwoord;
    private AccesLevel level;

    public CreateUserCommand(UserDatabase db, String name, String email,
            String wachtwoord, AccesLevel level) throws DomainException {
        this.db = db;
        this.setName(name);
        this.setEmail(email);
        this.setWachtwoord(wachtwoord);
        this.setLevel(level);
    }

    public void setName(String name) throws DomainException {
        name = name.trim();
        if(name.length() <= 0)
            throw new DomainException("Naam mag niet leeg zijn.");
        this.name = name;
    }

    public void setEmail(String email) throws DomainException {
        email = email.trim();
        if(email.length() <= 0)
            throw new DomainException("Email mag niet leeg zijn.");
        if(!EmailValidator.getInstance().isValid(email))
            throw new DomainException("Email is ongeldig");
        
        // Kijken of email al in gebruik is
        try {
            User user = this.db.getByEmail(email);
            if (user != null)
                throw new DomainException("Email is al in gebruik");
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }

        this.email = email;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public void setLevel(AccesLevel level) {
        if(level == null)
            level = AccesLevel.USER_CREATED;
        this.level = level;
    }

    public User execute() throws DomainException {
        User user = null;
        try {
            user = this.db.add(name, email, wachtwoord, level);
        } catch (DatabaseException e) {
            // TODO Auto-generated catch block
            throw new DomainException(e);
        }
        
        return user;
    }

}
