package framework.http.router;

import javax.servlet.ServletException;

import framework.http.Request;
import framework.http.Response;

public interface Resolver {
    public void resolve(Request request, Response response) throws ServletException;
}
