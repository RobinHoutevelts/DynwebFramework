package controller;

import controller.handlers.AjaxHandlerFactory;
import controller.handlers.Handler;
import controller.handlers.RequestHandlerFactory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = {"/c"})
public class FrontController extends HttpServlet {
    
    private RequestHandlerFactory requestHandlerFactory;
    private AjaxHandlerFactory ajaxHandlerFactory;
    
    @Override
    public void init() {
        this.requestHandlerFactory = (RequestHandlerFactory) this.getServletContext().getAttribute("requestHandlerFactory");
        this.ajaxHandlerFactory = (AjaxHandlerFactory) this.getServletContext().getAttribute("ajaxHandlerFactory");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int identifier = Integer.parseInt(request.getParameter("a"));
        Boolean ajax = (request.getParameter("x") != null);
        
        Handler handler = ajax ? ajaxHandlerFactory.getAjaxHandler(identifier) : requestHandlerFactory.getRequestHandler(identifier);
        String executeString = handler.execute(request, response);
        
        if (ajax) {
            try {
                response.setContentType("text/xml");
                response.getWriter().write(executeString);
            } catch (IOException exception) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, exception);
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher(executeString).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "FrontController-servlet for the website.";
    }
}