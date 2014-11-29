package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;
import database.DatabaseException;
import database.UserDatabase;
import domain.DomainException;
import domain.User;
import domain.AccesLevel;
import framework.Container;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserAjaxHandler extends AjaxHandler {

    private UserDatabase userDatabase;

    public BlockUserAjaxHandler(Container app) {
        super(app);
        this.userDatabase = (UserDatabase) app.make("UserDatabase");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession(true).getAttribute("user");
        if (user != null && user.isAdmin()) {
            if (request.getParameter("uid") == null) {
                return "Missing parameter 'uid' for ajax-call.";
            } else {
                try {
                    long id = Long.parseLong(request.getParameter("uid"));
                    User blocked = this.userDatabase.get(id);
                    if (blocked.isRemoved()) {
                        return "There is no user for the given 'uid'.";
                    } else if (blocked.isBlocked()) {
                        return "The user is already blocked.";
                    } else {
                        blocked.setAccesLevel(AccesLevel.BLOCKED);
                        return ""; //Succes
                    }
                } catch (NumberFormatException exception) {
                    return "Parameter 'uid' must be of type long.";
                } catch (DatabaseException | DomainException exception) {
                    return exception.getMessage();
                }
            }
        } else {
            return "You must be logged in as admin to block users.";
        }
    }
}