package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;
import database.DatabaseException;
import domain.User;
import service.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserAjaxHandler extends AjaxHandler {

    public GetUserAjaxHandler(WebService webService) {
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
                    User requestedUser = webService.getUser(id);
                    if (requestedUser.isRemoved()) {
                        return "There is no user for the given 'uid'.";
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                        stringBuilder.append("<user>");
                        stringBuilder.append("<id>").append(requestedUser.getId()).append("</id>");
                        stringBuilder.append("<name>").append(requestedUser.getName()).append("</name>");
                        stringBuilder.append("<email>").append(requestedUser.getEmail()).append("</email>");
                        stringBuilder.append("<level>").append(requestedUser.getLevel().getLevel()).append("</level>");
                        stringBuilder.append("</user>");
                        return stringBuilder.toString();
                    }
                } catch (NumberFormatException exception) {
                    return "Parameter 'uid' must be of type long.";
                } catch (DatabaseException exception) {
                    return exception.getMessage();
                }
            }
        } else {
            return "You must be logged in as admin to get user information.";
        }
    }
}
