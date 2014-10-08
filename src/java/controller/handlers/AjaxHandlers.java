package controller.handlers;

public enum AjaxHandlers {
    
    /**
     * Ajax-calls must always be handled. Therefor there isn't a default ajax-handler-class as for the normal request.
     * AjaxHandler is an abstract class, so don't use is as a default handler! Always specify a new handler class for a new ajax-requesttype.
     */
    USERS_ONLINE ("UsersOnlineAjaxHandler");
    
    private final String ajaxHandlerClass;
    
    AjaxHandlers (String ajaxHandlerClass) {
        this.ajaxHandlerClass = ajaxHandlerClass;
    }
        
    public String getAjaxHandlerClass() {
        return "controller.handlers.ajax." + this.ajaxHandlerClass;
    }
}
