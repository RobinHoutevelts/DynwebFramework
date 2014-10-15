package controller.handlers.ajax.question;

import controller.handlers.ajax.AjaxHandler;
import service.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllQuestionsAjaxHandler extends AjaxHandler {

    public GetAllQuestionsAjaxHandler(WebService webService) {
        super(webService);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
