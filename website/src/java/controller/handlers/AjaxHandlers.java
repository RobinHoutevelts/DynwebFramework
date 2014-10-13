package controller.handlers;

public enum AjaxHandlers {
    
    /**
     * Ajax-calls must always be handled. Therefor there isn't a default ajax-handler-class as for the normal request.
     * AjaxHandler is an abstract class, so don't use is as a default handler! Always specify a new handler class for a new ajax-requesttype.
     */
    GET_ALL_USERS   ("user", "GetAllUsersAjaxHandler"),
    GET_USER        ("user", "GetUserAjaxHandler"),
    BLOCK_USER      ("user", "BlockUserAjaxHandler"),
    UNBLOCK_USER    ("user", "UnblockUserAjaxHandler"),
    MAKE_USER_ADMIN ("user", "MakeUserAdminAjaxHandler"),
    DELETE_USER     ("user", "DeleteUserAjaxHandler"),
    
    GET_ALL_QUESTIONS           ("question", "GetAllQuestionsAjaxHandler"),
    GET_LATEST_QUESTIONID       ("question", "GetLatestQuestionId"),
    GET_DISAPPROVED_QUESTIONS   ("question", "GetDisapprovedQuestionsAjaxHandler"),
    GET_QUESTION                ("question", "GetQuestionAjaxHandler"),
    DELETE_QUESTION             ("question", "DeleteQuestionAjaxHandler"),
    APPROVE_QUESTION            ("question", "ApproveQuestionAjaxHandler"),
    DISAPPROVE_QUESTION         ("question", "DisapproveAjaxHandler");
    
    private final String ajaxHandlerClass;
    private final String packageName;
    
    AjaxHandlers (String packageName, String ajaxHandlerClass) {
        this.ajaxHandlerClass = ajaxHandlerClass;
        this.packageName = packageName;
    }
        
    public String getAjaxHandlerClass() {
        return "controller.handlers.ajax." + packageName + "." + this.ajaxHandlerClass;
    }
}
