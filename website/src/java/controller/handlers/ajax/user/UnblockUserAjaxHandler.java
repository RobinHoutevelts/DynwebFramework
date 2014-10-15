package controller.handlers.ajax.user;

import controller.handlers.ajax.AjaxHandler;
import database.Database;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockUserAjaxHandler extends AjaxHandler {

    public UnblockUserAjaxHandler(Database database) {
        super(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
