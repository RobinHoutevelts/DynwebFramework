package controller.handlers;

public enum AjaxHandlers {

    /**
     * Ajax-calls must always be handled. Therefor there isn't a default ajax-handler-class as for the normal request.
     * AjaxHandler is an abstract class, so don't use is as a default handler! Always specify a new handler class for a new ajax-requesttype.
     */
    LOGIN       ("", "LoginAjaxHandler"),
    REGISTER    ("", "RegisterAjaxHandler"),

    GET_ALL_USERS   ("user", "GetAllUsersAjaxHandler"),
    GET_USER        ("user", "GetUserAjaxHandler"),
    BLOCK_USER      ("user", "BlockUserAjaxHandler"),
    UNBLOCK_USER    ("user", "UnblockUserAjaxHandler"),
    MAKE_USER_ADMIN ("user", "MakeUserAdminAjaxHandler"),
    REMOVE_USER     ("user", "RemoveUserAjaxHandler"),
    GET_USERS_INFO  ("user", "GetUsersInfoAjaxHandler"),

    GET_ALL_QUESTIONS           ("question", "GetAllQuestionsAjaxHandler"),
    GET_LATEST_QUESTIONID       ("question", "GetLatestQuestionidAjaxHandler"),
    GET_DISAPPROVED_QUESTIONS   ("question", "GetDisapprovedQuestionsAjaxHandler"),
    GET_QUESTION                ("question", "GetQuestionAjaxHandler"),
    REMOVE_QUESTION             ("question", "RemoveQuestionAjaxHandler"),
    APPROVE_QUESTION            ("question", "ApproveQuestionAjaxHandler"),
    DISAPPROVE_QUESTION         ("question", "DisapproveQuestionAjaxHandler"),
    GET_QUESTIONS_INFO          ("question", "GetQuestionsInfoAjaxHandler");

    private final String ajaxHandlerClass;
    private final String packageName;

    AjaxHandlers (String packageName, String ajaxHandlerClass) {
        this.ajaxHandlerClass = ajaxHandlerClass;
        this.packageName = packageName;
    }

    public String getAjaxHandlerClass() {
        if (packageName.equals("")) return "controller.handlers.ajax." + this.ajaxHandlerClass;
        else return "controller.handlers.ajax." + packageName + "." + this.ajaxHandlerClass;
    }
}