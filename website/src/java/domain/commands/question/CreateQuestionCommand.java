package domain.commands.question;

import database.DatabaseException;
import database.QuestionDatabase;
import database.UserDatabase;
import domain.DomainException;
import domain.Question;
import domain.User;

public class CreateQuestionCommand {

    private QuestionDatabase questionDb;
    private UserDatabase userDb;

    protected User user;
    protected String text;
    public CreateQuestionCommand(QuestionDatabase questionDb,
            UserDatabase userDb, long userId, String text) throws DomainException {
        
        this.questionDb = questionDb;
        this.userDb = userDb;
        
        this.setUser(userId);
        this.setText(text);
    }
    
    public Question execute() throws DomainException{
        Question question = null;
        try {
            question = this.questionDb.add(user, text);
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
        
        return question;
    }

    public void setUser(long userId) throws DomainException {
        if (userId <= 0)
            throw new DomainException("Id mag niet kleiner zijn dan 1.");

        try {
            this.user = this.userDb.get(userId);
            if (this.user.isRemoved())
                throw new DomainException("Gebruiker is verwijderd en kan geen vragen stellen.");
            if (this.user.isBlocked())
                throw new DomainException("Gebruiker is geblokkeerd en kan geen vragen stellen.");
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

        this.text = text;
    }

}
