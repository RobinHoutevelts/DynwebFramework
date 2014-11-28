package controller.handlers.ajax.question;

import controller.handlers.ajax.AjaxHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IoC;

public class GetQuestionsInfoAjaxHandler extends AjaxHandler {

    public GetQuestionsInfoAjaxHandler(IoC app) {
        super(app);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
