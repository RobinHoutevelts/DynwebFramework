package controller.handlers;

public enum RequestHandlers {
    
    /**
     * If no specific request-handler-class is specified, use "RequestHandler" as default request-handler-class.
     */
    HOME        ("home.jsp", "RequestHandler"),
    LOGIN       ("login.jsp", "RequestHandler"),
    QUESTIONS   ("questions.jsp", "RequestHandler"),
    
    //Admin RequestHandlers
    ADMIN_OVERVIEW  ("adminOverview.jsp", "RequestHandler"),
    ADMIN_ANALYTICS ("adminAnalytics.jsp", "RequestHandler"),
    ADMIN_SITELOG   ("adminSitelog.jsp", "RequestHandler"),
    ADMIN_SETTING   ("adminSettings.jsp", "RequestHandler"),
    ADMIN_USERS     ("adminUsers.jsp", "RequestHandler"),
    ADMIN_QUESTIONS ("adminQuestions.jsp", "RequestHandler");
    
    private final String view;
    private final String requestHandlerClass;
    
    RequestHandlers(String view, String requestHandlerClass) {
        this.view = view;
        this.requestHandlerClass = requestHandlerClass;
    }
    
    public String getView() {
        return this.view;
    }
    
    public String getRequestHandlerClass() {
        return "controller.handlers.request." + this.requestHandlerClass;
    }
}