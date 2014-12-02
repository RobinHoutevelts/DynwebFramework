package domain.commands.user;

import database.DatabaseException;
import database.UserDatabase;
import domain.DomainException;
import domain.User;

public class ChangePasswordCommand {

    private UserDatabase db;

    private User   userObject;
    private String wachtwoord;

    public ChangePasswordCommand(UserDatabase db, long userId, String wachtwoord) throws DomainException {
        this.db = db;
        
        this.setUser(userId);
        this.setWachtwoord(wachtwoord);
    }
    
    public User execute() throws DomainException {
        try {
            this.db.changePassword(this.userObject,wachtwoord);
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

    public void setWachtwoord(String wachtwoord) throws DomainException {
        if(wachtwoord == null)
            return;
        
        wachtwoord = wachtwoord.trim();
        if(wachtwoord.length() <= 6)
            throw new DomainException("Wachtwoord moet minstens 6 karakters bevatten.");
        
        this.wachtwoord = wachtwoord;
    }

}
