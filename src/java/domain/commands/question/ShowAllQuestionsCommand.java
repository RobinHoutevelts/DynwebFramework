package domain.commands.question;

import java.util.List;

import database.DatabaseException;
import database.QuestionDatabase;
import domain.DomainException;
import domain.Question;

public class ShowAllQuestionsCommand {
    
    private QuestionDatabase db;
    private int page;
    private int perPage;
    
    //TODO searchFilter

    public ShowAllQuestionsCommand(QuestionDatabase db){
        this(db, 1, 25);
    }

    public ShowAllQuestionsCommand(QuestionDatabase db, int page) {
        this(db, page, 25);
    }
    
    public ShowAllQuestionsCommand(QuestionDatabase db, int page, int perPage) {
        this.db = db;
        this.page = page;
        this.perPage = page;
    }
    
    public List<Question> execute() throws DomainException {
        List<Question> questions = null;
        
        int offset = this.page-1 * this.perPage;
        
        try {
            questions = (List<Question>) this.db.getAll(offset, perPage);
        } catch (DatabaseException e) {
            throw new DomainException(e);
        }
        
        return questions;
    }
    
    
}
