package controller.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IoC;

public abstract class Handler {

    protected IoC app;

    public Handler(IoC app) {
        this.app = app;
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}