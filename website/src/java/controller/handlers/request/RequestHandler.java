package controller.handlers.request;

import controller.handlers.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Container;

public class RequestHandler extends Handler {

    private String view;

    public RequestHandler(Container app, String view) {
        super(app);
        this.view = view;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return this.getView();
    }

    public String getView() {
        return this.view;
    }

    protected void setView(String view) {
        this.view = view;
    }
}