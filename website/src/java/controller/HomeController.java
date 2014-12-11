package controller;

import javax.servlet.ServletException;

import framework.Container;
import framework.http.controllers.Controller;

public class HomeController extends Controller{

    public HomeController(Container app) {
        super(app);
    }
    
    public void index() throws ServletException {
        this.dispatch("welcome.jsp");
    }

    
}
