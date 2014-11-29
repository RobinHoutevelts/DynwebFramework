package database;

import domain.DomainException;
import domain.Question;
import domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCQuestionDatabase implements QuestionDatabase {
    protected Database database;
    protected UserDatabase userDatabase;
    public JDBCQuestionDatabase(Database database, UserDatabase userdatabase) {
        this.database = database;
        this.userDatabase = userdatabase;
    }
    
    public Question add(User user, String text) throws DatabaseException{
        return this.add(user, text, false, false, false);
    }

    @Override
    public Question add(User user, String text, boolean approved,
            boolean reviewed, boolean removed) throws DatabaseException {
        
        
        String sql = "INSERT INTO Questions (userid,text,reviewed,approved,removed)"
                + " VALUES(:userId,:text,:reviewed,:approved,:removed)";
        
        int rowsAffected = 0;
        
        try{
            NamedParamStatement stmt = this.database.namedParamStatement(sql);
            
            stmt.setLong("userId", user.getId());
            stmt.setString("text", text);
            stmt.setBoolean("reviewed", reviewed);
            stmt.setBoolean("approved", approved);
            stmt.setBoolean("removed", removed);
            
            rowsAffected = stmt.executeUpdate();
            
            stmt.close();
            
        }catch(SQLException e){
            throw new DatabaseException(e);
        }
        
        if(rowsAffected <= 0){
            throw new DatabaseException("Kon vraag niet toevoegen aan databank.");
        }
        
        Question question = null;
        List<Question> questions = this.getByUser(user, text);
                
        for(Question q : questions){
            if(!q.isReviewed()){
                question = q;
                break;
            }
        }
        
        if(question == null){
            throw new DatabaseException("Kon toegevoegde vraag niet meer terugvinden in databank.");
        }
        
        return question;
    }
    
    public List<Question> getByUser(User user) throws DatabaseException{
        ArrayList<Question> questions = new ArrayList<Question>();
        
        String sql = "SELECT id,userid,text,reviewed,approved,removed"
                + " FROM Questions"
                + " WHERE userid = :userId";
        
        try{
            NamedParamStatement stmt = this.database.namedParamStatement(sql);
            stmt.setLong("userId", user.getId());
            
            // Prepared Statement uitvoeren
            ResultSet res = stmt.executeQuery();
            
            // Gegevens ophalen            
            ArrayList<DatabaseRow> rows = this.database.getAllRows(res);
            
            for(DatabaseRow row : rows){
                // Object aanmaken
                long id = row.getLong("id");
                String text = row.getString("text");
                boolean reviewed = row.getBoolean("reviewed");
                boolean approved = row.getBoolean("approved");
                boolean removed = row.getBoolean("removed");
                
                Question question = new Question(id, user, text, approved, reviewed, removed);
                
                questions.add(question);
            }
            stmt.close();
        }catch(SQLException | DomainException e){
            throw new DatabaseException(e);
        }
        
        return questions;
    }
    
    public List<Question> getByUser(User user, String text) throws DatabaseException{
        ArrayList<Question> questions = new ArrayList<Question>();
        
        String sql = "SELECT id,userid,text,reviewed,approved,removed"
                + " FROM Questions"
                + " WHERE userid = :userId"
                + " AND   text   = :text";
        
        try{
            NamedParamStatement stmt = this.database.namedParamStatement(sql);
            stmt.setLong("userId", user.getId());
            stmt.setString("text", text);
            
            // Prepared Statement uitvoeren
            ResultSet res = stmt.executeQuery();
            
            // Gegevens ophalen            
            ArrayList<DatabaseRow> rows = this.database.getAllRows(res);
            
            for(DatabaseRow row : rows){
                // Object aanmaken
                
                long id = row.getLong("id");
                text = row.getString("text");
                boolean reviewed = row.getBoolean("reviewed");
                boolean approved = row.getBoolean("approved");
                boolean removed = row.getBoolean("removed");
                
                Question question = new Question(id, user, text, approved, reviewed, removed);
                
                questions.add(question);
            }
            stmt.close();
        }catch(SQLException | DomainException e){
            throw new DatabaseException(e);
        }
        
        return questions;
    }

    @Override
    public void remove(Question question) throws DatabaseException {
        String sql = "DELETE FROM Questions where id = :id";
        int rowsAffected = 0;
        
        try{
            NamedParamStatement stmt = this.database.namedParamStatement(sql);
            stmt.setLong("id", question.getId());
            
            rowsAffected = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        
        if(rowsAffected <= 0){
            throw new DatabaseException("Kon question met id '"+question.getId()+"' niet verwijderen.");
        }
        
        question.remove();
    }

    @Override
    public void update(Question question) throws DatabaseException {
        String sql = "UPDATE Questions SET"
                + " userid = :userId,"
                + " text = :text,"
                + " reviewed = :reviewed,"
                + " approved = :approved,"
                + " removed = :removed"
                + " WHERE id = :id";
        try{
            NamedParamStatement stmt = this.database.namedParamStatement(sql);
            stmt.setLong("id", question.getId());
            stmt.setLong("userId", question.getUser().getId());
            stmt.setString("text", question.getText());
            stmt.setBoolean("reviewed", question.isReviewed());
            stmt.setBoolean("approved", question.isApproved());
            stmt.setBoolean("removed", question.isRemoved());
            
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public Question get(long id) throws DatabaseException {
        String sql = "SELECT id,userid,text,reviewed,approved,removed"
                + " FROM Questions"
                + " WHERE id = :id";
        
        Question question = null;
        
        try{
            NamedParamStatement stmt = this.database.namedParamStatement(sql);
            stmt.setLong("id", id);
            
            // Prepared Statement uitvoeren
            ResultSet res = stmt.executeQuery();
            
            // Gegevens ophalen            
            DatabaseRow row = this.database.getRow(res);
            
            if(row == null){
                throw new DatabaseException("Kon vraag met id '"+id+"' niet vinden in de databank.");
            }
            
            id = row.getLong("id");
            long userId = row.getLong("userid");
            String text = row.getString("text");
            boolean reviewed = row.getBoolean("reviewed");
            boolean approved = row.getBoolean("approved");
            boolean removed = row.getBoolean("removed");
            
            User user = this.userDatabase.get(userId);
            
            question = new Question(id, user, text, approved, reviewed, removed);
            
            stmt.close();
        }catch(SQLException | DomainException e){
            throw new DatabaseException(e);
        }
        
        return question;
    }

    @Override
    public Collection<Question> getAll(int offset, int rowCount) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
