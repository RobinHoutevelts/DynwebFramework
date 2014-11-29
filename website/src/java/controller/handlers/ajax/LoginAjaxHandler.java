package controller.handlers.ajax;

import database.DatabaseException;
import database.UserDatabase;
import domain.User;
import framework.Container;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAjaxHandler extends AjaxHandler {

    private UserDatabase userDatabase;
    public LoginAjaxHandler(Container app) {
        super(app);
        this.userDatabase = (UserDatabase) app.make("UserDatabase");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message = "";
        User user = null;
        
        try{
            if(this.userDatabase.getByEmail(email) == null){
                message = "User is not found."; // Wordt wss niet uitgevoerd door exception
            }else{
                // Gebruiker zoeken met e-mail en wachtwoord
                user = this.userDatabase.getByCredentials(email, password);
                password = null; // Uit het geheugen halen
                
                if (user == null) {
                    message = "Password is not correct.";
                }
            }
        }catch(DatabaseException e){
            message = e.getMessage();
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
