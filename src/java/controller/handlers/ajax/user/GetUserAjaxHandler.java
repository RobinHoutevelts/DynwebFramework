package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;
import service.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserAjaxHandler extends AjaxHandler {

    public GetUserAjaxHandler(WebService webService) {
        super(webService);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
