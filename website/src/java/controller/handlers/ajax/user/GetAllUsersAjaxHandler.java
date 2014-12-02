package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;
import database.DatabaseException;
import database.UserDatabase;
import domain.User;
import framework.Container;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllUsersAjaxHandler extends AjaxHandler {

    private UserDatabase userDatabase;
    public GetAllUsersAjaxHandler(Container app) {
        super(app);
        this.userDatabase = (UserDatabase) this.app.make("UserDatabase");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession(true).getAttribute("user");
        if (user != null && user.isAdmin()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            stringBuilder.append("<users>");
            try {
                for (User u : this.userDatabase.getAll(1,1)) {
                    if (u.isRemoved()) continue;
                    stringBuilder.append("<user>");
                    stringBuilder.append("<id>").append(u.getId()).append("</id>");
                    stringBuilder.append("<name>").append(u.getName()).append("</name>");
                    stringBuilder.append("<email>").append(u.getEmail()).append("</email>");
                    stringBuilder.append("<level>").append(u.getLevel().getLevel()).append("</level>");
                    stringBuilder.append("</user>");
                }
            } catch (DatabaseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            stringBuilder.append("</users>");
            return stringBuilder.toString();
        } else {
            return "You must be logged in as admin to get user information.";
        }
    }
}
