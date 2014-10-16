package controller.handlers.ajax.question;

import controller.handlers.ajax.AjaxHandler;

import domain.Question;
import domain.User;

import service.WebService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllQuestionsAjaxHandler extends AjaxHandler {

    public GetAllQuestionsAjaxHandler(WebService webService) {
        super(webService);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession(true).getAttribute("user");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuilder.append("<questions>");
        for (Question q : webService.getAllQuestions()) {
            if (q.isRemoved()) continue;
            stringBuilder.append("<question>");
            stringBuilder.append("<id>").append(q.getId()).append("</id>");
            stringBuilder.append("<text>").append(q.getText()).append("</text>");
            stringBuilder.append("<approved>").append(q.isApproved()).append("</approved>");
            stringBuilder.append("<reviewed>").append(q.isReviewed()).append("</reviewed>");
            stringBuilder.append("<userid>").append(q.getUser().getId()).append("</userid>");
            stringBuilder.append("<name>").append(q.getUser().getName()).append("</name>");
            stringBuilder.append("<level>").append(q.getUser().getLevel().getLevel()).append("</level>");
            if (user != null && user.isAdmin()) stringBuilder.append("<email>").append(q.getUser().getEmail()).append("</email>");
            stringBuilder.append("</user>");
        }
        stringBuilder.append("</users>");
        return stringBuilder.toString();
    }
}
