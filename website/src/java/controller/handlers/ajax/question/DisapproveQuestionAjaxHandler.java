package controller.handlers.ajax.question;

import controller.handlers.ajax.AjaxHandler;
import service.IoC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisapproveQuestionAjaxHandler extends AjaxHandler {

    public DisapproveQuestionAjaxHandler(IoC app) {
        super(app);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
