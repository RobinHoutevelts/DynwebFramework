package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IoC;

public class GetUsersInfoAjaxHandler extends AjaxHandler {

    public GetUsersInfoAjaxHandler(IoC app) {
        super(app);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession(true).getAttribute("user");
        if (user != null && user.isAdmin()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            stringBuilder.append("<info>");
            stringBuilder.append("<all>").append("").append("</all>");
            stringBuilder.append("<admins>").append("").append("</admins>");
            stringBuilder.append("<users>").append("").append("</users>");
            stringBuilder.append("<blocked>").append("").append("</blocked>");
            stringBuilder.append("</info>");
            return stringBuilder.toString();
        } else {
            return "You must be logged in as admin to get user information.";
        }
    }

}
