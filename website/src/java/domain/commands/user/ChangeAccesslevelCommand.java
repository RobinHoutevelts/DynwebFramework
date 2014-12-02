package domain.commands.user;

import database.DatabaseException;
import database.UserDatabase;
import domain.AccesLevel;
import domain.DomainException;
import domain.User;

public class ChangeAccesslevelCommand {

    private UserDatabase db;

    private User   userObject;

    public ChangeAccesslevelCommand(UserDatabase db, long userId, AccesLevel level) throws DomainException {
        this.db = db;
        
        this.setUser(userId);
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

    public void setLevel(AccesLevel level) throws DomainException {
        if(level == null)
            throw new DomainException("Acceslevel mag niet null zijn");
        
        // TODO: currentuser toegankelijk maken vanuit Commands
        // TODO: kijken of currentuser rechten heeft.
        this.userObject.setAccesLevel(level);
    }

}
