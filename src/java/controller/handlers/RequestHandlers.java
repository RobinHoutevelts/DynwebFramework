package controller.handlers;

public enum RequestHandlers {
    
    /**
     * If no specific request-handler-class is specified, use "RequestHandler" as default request-handler-class.
     */
    HOME ("home.jsp", "RequestHandler");
    
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