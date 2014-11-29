package domain.commands.user;

import java.util.List;

import database.DatabaseException;
import database.UserDatabase;
import domain.DomainException;
import domain.User;

public class ShowAllUsersCommand {

    private UserDatabase db;

    private int perPage = 25;
    private int page = 1;

    // TODO searchFilter

    public ShowAllUsersCommand(UserDatabase db) {
        this(db, 1, 25);
    }

    public ShowAllUsersCommand(UserDatabase db, int page) {
        this(db, page, 25);
    }

    public ShowAllUsersCommand(UserDatabase db, int page, int perPage) {
        this.db = db;
        this.page = page;
        this.perPage = page;
    }

    public List<User> execute() throws DomainException {
        List<User> users = null;
        
        int offset = this.page-1 * this.perPage;
        
        try {
            users = (List<User>) this.db.getAll(offset, perPage);
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
        
        return users;
    }

}
