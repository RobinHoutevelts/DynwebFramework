package domain.commands.question;

import database.DatabaseException;
import database.QuestionDatabase;
import domain.DomainException;
import domain.Question;

public class UpdateQuestionCommand {

    private QuestionDatabase questionDb;

    protected Question question;

    public UpdateQuestionCommand(QuestionDatabase questionDb, long questionId,String text) throws DomainException {

        this.questionDb = questionDb;

        this.setQuestion(questionId);
        this.setText(text);
    }

    public Question execute() throws DomainException {
        
        // TODO: kijken of huidige gebruiker genoeg rechten heeft
        
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

    public void setText(String text) throws DomainException {
        if (text == null)
            throw new DomainException("Text mag niet null zijn.");
        
        text = text.trim();

        if (text.length() <= 0)
            throw new DomainException("Text mag niet leeg zijn.");

        this.question.setText(text);
    }



}
