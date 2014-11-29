package controller.handlers.ajax;

import controller.handlers.Handler;
import framework.Container;

public abstract class AjaxHandler extends Handler {

    public AjaxHandler(Container app) {
        super(app);
    }
}