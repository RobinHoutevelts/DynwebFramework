package controller.handlers.ajax;

import database.DatabaseException;
import database.UserDatabase;
import domain.User;

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
        
        User user = null;
        try{
            user = this.userDatabase.getByEmail(email);
        }catch(DatabaseException e){
            message = e.getMessage();
        }
        
        if (user != null) {
           try{
               user = null;
               user = this.userDatabase.getByCredentials(email, password);
           }catch(DatabaseException e){
               message = e.getMessage();
           }
        }
        
        if (user != null) {
            if (user.isBlocked()) {
                message = "Account is blocked.";
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("alert", "You have logged in successfully.");
                session.setAttribute("alertType", "info");
                message = "";
            }
        }

        return "<message>" + message + "</message>";
    }
}
