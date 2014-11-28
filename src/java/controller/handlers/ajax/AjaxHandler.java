package controller.handlers.ajax;

import controller.handlers.Handler;
import service.IoC;

public abstract class AjaxHandler extends Handler {

    public AjaxHandler(IoC app) {
        super(app);
    }
}