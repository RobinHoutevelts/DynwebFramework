package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;

import database.DatabaseException;
import database.UserDatabase;

import service.WebService;

import domain.User;
import domain.AccesLevel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserAjaxHandler extends AjaxHandler {

    private UserDatabase userDatabase;
    
    public BlockUserAjaxHandler(WebService webService) {
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
                    User blocked = webService.getUser(id);
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
                } catch (DatabaseException exception) {
                    return exception.getMessage();
                }
            }
        } else {
            return "You must be logged in as admin to block users.";
        }
    }
}