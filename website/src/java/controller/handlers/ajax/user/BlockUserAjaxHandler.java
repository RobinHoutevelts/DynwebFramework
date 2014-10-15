package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;
import service.WebService;
import domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserAjaxHandler extends AjaxHandler{

    public BlockUserAjaxHandler(WebService webService) {
        super(webService);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession(true).getAttribute("user");
        if (user != null && user.isAdmin()) {
            
            return "";
        } else {
            return "You must be logged in as admin to block users.";
        }
    }
}