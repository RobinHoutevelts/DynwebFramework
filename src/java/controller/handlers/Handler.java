package controller.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Container;

public abstract class Handler {

    protected Container app;

    public Handler(Container app) {
        this.app = app;
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}