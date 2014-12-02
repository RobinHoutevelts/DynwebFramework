package domain.commands.user;

import org.apache.commons.validator.routines.EmailValidator;

import database.DatabaseException;
import database.UserDatabase;
import domain.AccesLevel;
import domain.DomainException;
import domain.User;

public class UpdateUserCommand {

    private UserDatabase db;

    private User   userObject;

    public UpdateUserCommand(UserDatabase db, long userId, String name, String email, AccesLevel level) throws DomainException {
        this.db = db;
        
        this.setUser(userId);
        this.setName(name);
        this.setEmail(email);
        this.setLevel(level);
    }
    
    public User execute() throws DomainException {
        try {
            this.db.update(this.userObject);
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
        
        return this.userObject;
    }
    
    public void setUser(long userId) throws DomainException{
        if (userId <= 0)
            throw new DomainException("Id mag niet kleiner zijn dan 1.");
        
        try {
            this.userObject = this.db.get(userId);
            if(this.userObject.isRemoved())
                throw new DomainException("Gebruiker is verwijderd.");
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
    }
    
    public void setName(String name) throws DomainException {
        if(name == null)
            throw new DomainException("Naam mag niet null zijn.");
        
        name = name.trim();
        if(name.length() <= 0)
            throw new DomainException("Naam mag niet leeg zijn.");
        
        this.userObject.setName(name);
    }

    public void setEmail(String email) throws DomainException {
        if(email == null)
            throw new DomainException("Email mag niet null zijn.");
        
        email = email.trim();
        if(email.length() <= 0)
            throw new DomainException("Email mag niet leeg zijn.");
        if(!EmailValidator.getInstance().isValid(email))
            throw new DomainException("Email is ongeldig");
        
        // Kijken of email al in gebruik is
        try {
            User user = this.db.getByEmail(email);
            if (user != null && user.getId() != this.userObject.getId())
                throw new DomainException("Email is al in gebruik");
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }

        this.userObject.setEmail(email);
    }

    public void setWachtwoord(String wachtwoord) throws DomainException {
        if(wachtwoord == null)
            return;
        
        wachtwoord = wachtwoord.trim();
        if(wachtwoord.length() <= 6)
            throw new DomainException("Wachtwoord moet minstens 6 karakters bevatten.");
        
        try {
            this.db.changePassword(this.userObject,wachtwoord);
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
    }

    public void setLevel(AccesLevel level) throws DomainException {
        if(level == null)
            throw new DomainException("Acceslevel mag niet null zijn");
        
        // TODO: currentuser toegankelijk maken vanuit Commands
        // TODO: kijken of currentuser rechten heeft.
        this.userObject.setAccesLevel(level);
    }

}
