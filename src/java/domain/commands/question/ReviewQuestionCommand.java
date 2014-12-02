package domain.commands.question;

import database.DatabaseException;
import database.QuestionDatabase;
import domain.DomainException;
import domain.Question;

public class ReviewQuestionCommand {

    private QuestionDatabase questionDb;

    protected Question question;

    public ReviewQuestionCommand(QuestionDatabase questionDb, long questionId, boolean approved)
            throws DomainException {

        this.questionDb = questionDb;

        this.setQuestion(questionId);
        this.review(approved);
    }

    public Question execute() throws DomainException {
        try {
            this.questionDb.update(this.question);
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }

        return this.question;
    }

    public void setQuestion(long questionId) throws DomainException {
        if (questionId <= 0)
            throw new DomainException("Id mag niet kleiner zijn dan 1.");

        try {
            this.question = this.questionDb.get(questionId);
            if (this.question.isRemoved())
                throw new DomainException("Vraag is verwijderd");

        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
    }


    public void review(boolean approved) throws DomainException {
        if (approved == this.question.isApproved())
            return;
        
        // TODO: kijken of currentUser genoeg rechten heeft
    
        if (this.question.isRemoved())
            throw new DomainException("Kan niet approven want vraag is verwijderd");
    
        if (approved)
            this.question.approve();
        else
            this.question.disapprove();
    }



}
