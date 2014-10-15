package controller.handlers.ajax;

import controller.handlers.Handler;
import service.WebService;

public abstract class AjaxHandler extends Handler {

    public AjaxHandler(WebService webService) {
        super(webService);
    }
}