package domain.commands.question;

import database.DatabaseException;
import database.QuestionDatabase;
import domain.DomainException;
import domain.Question;

public class RemoveQuestionCommand {
    private QuestionDatabase db;

    private Question question;

    public RemoveQuestionCommand(QuestionDatabase db, long id)
            throws DomainException {
        this.db = db;
        this.setQuestion(id);
    }

    public void setQuestion(long id) throws DomainException {
        if (id <= 0)
            throw new DomainException("Id moet groter zijn dan 0");

        try {
            Question question = this.db.get(id);
            this.question = question;
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }

    }

    public void execute() throws DomainException {
        try {
            this.db.remove(this.question);
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
    }
}
