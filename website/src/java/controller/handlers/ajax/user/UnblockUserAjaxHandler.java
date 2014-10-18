package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;
import database.DatabaseException;
import domain.AccesLevel;
import domain.User;
import service.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockUserAjaxHandler extends AjaxHandler {

    public UnblockUserAjaxHandler(WebService webService) {
        super(webService);
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
                    User unblocked = webService.getUser(id);
                    if (unblocked.isRemoved()) {
                        return "There is no user for the given 'uid'.";
                    } else if (unblocked.isBlocked()) {
                        unblocked.setAccesLevel(AccesLevel.USER_ACTIVATED);
                        return ""; //Succes
                    } else {
                        return "The user is not blocked.";
                    }
                } catch (NumberFormatException exception) {
                    return "Parameter 'uid' must be of type long.";
                } catch (DatabaseException exception) {
                    return exception.getMessage();
                }
            }
        } else {
            return "You must be logged in as admin to unblock users.";
        }
    }
}
