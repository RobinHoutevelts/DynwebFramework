package domain.commands.user;

import database.DatabaseException;
import database.UserDatabase;
import domain.DomainException;
import domain.User;

public class RemoveUserCommand {

    private UserDatabase db;

    private User user;

    public RemoveUserCommand(UserDatabase db, long id) throws DomainException
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
        
    }

    public void execute() throws DomainException {
        try {
            this.db.remove(this.user);
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
    }

}
