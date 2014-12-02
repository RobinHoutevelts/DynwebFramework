package domain.commands.user;

import database.DatabaseException;
import database.UserDatabase;
import domain.AccesLevel;
import domain.DomainException;
import domain.User;

public class MakeAdminCommand {
    
    private UserDatabase db;
    private User user;
    
    public MakeAdminCommand(UserDatabase db, long id) throws DomainException
    {
        this.db = db;
        this.setUser(id);
    }

    public void setUser(long id) throws DomainException {
        if(id <= 0)
            throw new DomainException("Id moet groter zijn dan 0");
        
        try {
            User user = this.db.get(id);
            this.user = user;
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
        
        if(user.isRemoved())
            throw new DomainException("Gebruiker is verwijderd.");
        if(user.isBlocked())
            throw new DomainException("Gebruiker is geblokkeerd.");
    }

    public void execute() throws DomainException {
        if(this.user.isAdmin())
            return;
        
        try {
            this.user.setAccesLevel(AccesLevel.BLOCKED);
            this.db.update(this.user);
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
    }

}