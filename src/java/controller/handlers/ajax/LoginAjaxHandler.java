package controller.handlers.ajax;

import database.UserDatabase;
import domain.User;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.WebService;

public class LoginAjaxHandler extends AjaxHandler {

    private UserDatabase userDatabase;
    public LoginAjaxHandler(WebService webService) {
        super(webService);
        this.userDatabase = webService.getUserDatabase();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message = "User is not found.";
        for (User u : this.userDatabase.getAll()) {
            if (u.isRemoved()) continue;
            if (u.getEmail().equals(email)) {
                message = "Password is not correct.";
                if (u.getPassword().equals(password)) {
                    if (u.isBlocked()) {
                        message = "Account is blocked.";
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", u);
                        session.setAttribute("alert", "You have logged in successfully.");
                        session.setAttribute("alertType", "info");
                        message = "";
                        break;
                    }
                }
            }
        }
        return "<message>" + message + "</message>";
    }
}
