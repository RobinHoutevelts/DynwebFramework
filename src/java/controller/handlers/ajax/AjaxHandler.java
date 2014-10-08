package controller.handlers.ajax;

import controller.handlers.Handler;
import database.Database;

public abstract class AjaxHandler extends Handler {

    public AjaxHandler(Database database) {
        super(database);
    }
}