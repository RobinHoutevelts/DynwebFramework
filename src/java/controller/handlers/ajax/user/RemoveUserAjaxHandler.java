package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;
import database.DatabaseException;
import database.UserDatabase;
import domain.User;
import service.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveUserAjaxHandler extends AjaxHandler {

    private UserDatabase userDatabase;
    public RemoveUserAjaxHandler(WebService webService) {
        super(webService);
        this.userDatabase = webService.getUserDatabase();
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
                    User removed = this.userDatabase.get(id);
                    if (removed.isRemoved()) {
                        return "There is no user for the given 'uid'.";
                    } else {
                        removed.remove();
                        return ""; //Succes
                    }
                } catch (NumberFormatException exception) {
                    return "Parameter 'uid' must be of type long.";
                } catch (DatabaseException exception) {
                    return exception.getMessage();
                }
            }
        } else {
            return "You must be logged in as admin to remove users.";
        }
    }
}
