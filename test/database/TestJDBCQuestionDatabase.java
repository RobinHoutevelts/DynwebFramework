package database;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import conf.Config;
import domain.AccesLevel;
import domain.DomainException;
import domain.Question;
import domain.User;

public class TestJDBCQuestionDatabase {
    
    private Database db;
    private QuestionDatabase questionDb;
    
    private User admin;
    private User blockedUser;
    private User createdUser;
    private User activatedUserA;
    private User activatedUserB;
    private User activatedUserC;

    @Before
    public void setUp() throws Exception {
        Config.load();
        
        String dbDriver = Config.get("dbDriver");
        String dbConnectionUrl = Config.get("dbConnectionUrl");
        String dbUsername = Config.get("dbUsername");
        String dbPassword = Config.get("dbPassword");

        this.db = new Database(dbDriver, dbConnectionUrl, dbUsername,dbPassword);
        UserDatabase userDb = new JDBCUserDatabase(this.db);
        
        this.questionDb = new JDBCQuestionDatabase(this.db,userDb);
        
        // Maak een paar users aan
        admin = userDb.add("Administrator", "admin@student.khleuven.be", "passwordHash", AccesLevel.ADMIN);
        blockedUser = userDb.add("BlockedUser", "blocked@student.khleuven.be", "passwordHash", AccesLevel.BLOCKED);
        createdUser = userDb.add("Created", "created@student.khleuven.be", "passwordHash", AccesLevel.USER_CREATED);
        activatedUserA = userDb.add("UserA", "UserA@student.khleuven.be", "passwordHash", AccesLevel.USER_ACTIVATED);
        activatedUserB = userDb.add("UserB", "UserB@student.khleuven.be", "passwordHash", AccesLevel.USER_ACTIVATED);
        activatedUserC = userDb.add("UserC", "UserC@student.khleuven.be", "passwordHash", AccesLevel.USER_ACTIVATED);
    }

    @After
    public void tearDown() throws Exception {

        // Tabel leegmaken
        this.db.prepareStatement("TRUNCATE Questions").execute();
        this.db.prepareStatement("TRUNCATE Users").execute();
        
        
        this.db.closeConnection();
    }
    
    @Test
    public void test_it_can_add_questions() throws DatabaseException {
        User user = this.activatedUserA;
        String text = "Is dit een vraag?";
        boolean approved = false;
        boolean reviewed = false;
        boolean removed  = false;
        
        Question question = this.questionDb.add(user, text);
        
        assertEquals(1, question.getId());
        assertEquals(user, question.getUser());
        assertEquals(text, question.getText());
        assertEquals(approved, question.isApproved());
        assertEquals(reviewed, question.isReviewed());
        assertEquals(removed, question.isRemoved());
        
        user = this.activatedUserB;
        text = "Dit is ook een vraag?";
        question = this.questionDb.add(user, text);
        
        assertEquals(2, question.getId());
        assertEquals(user, question.getUser());
        assertEquals(text, question.getText());
        assertEquals(approved, question.isApproved());
        assertEquals(reviewed, question.isReviewed());
        assertEquals(removed, question.isRemoved());
    }
    
    @Test(expected=DatabaseException.class)
    public void test_it_removes_questions() throws DatabaseException {
        User user = this.activatedUserA;
        String text = "Is dit een vraag?";
        
        Question question = this.questionDb.add(user, text);
        
        this.questionDb.remove(question);
        
        assertTrue(question.isRemoved());
        
        // Moet exception gooien want question is removed
        this.questionDb.get(question.getId());
    }
    
    @Test
    public void test_it_updates_a_question() throws DatabaseException, DomainException {
        User user = this.activatedUserA;
        String text = "Is dit een vraag?";
        
        Question question = this.questionDb.add(user, text);
        
        question.setText("Ik herformuleer mn vraag.");
        question.approve();
        
        this.questionDb.update(question);
        
        Question questionB = this.questionDb.get(question.getId());
        
        assertEquals(question, questionB);
    }
    
    @Test
    public void test_it_gets_a_question_by_id() throws DatabaseException {
        User user = this.activatedUserA;
        String text = "Is dit een vraag?";
        
        Question questionA = this.questionDb.add(user, text);
        Question questionB = this.questionDb.get(questionA.getId());
        
        assertEquals(questionA, questionB);
    }
    
    @Test
    public void test_it_gets_questions_from_a_user() throws DatabaseException {
        User userA = this.activatedUserA;
        User userB = this.activatedUserB;
        User userC = this.activatedUserC;
        
        Question questionA = this.questionDb.add(userA, "Vraag A");
        Question questionB = this.questionDb.add(userA, "Vraag B");
        Question questionC = this.questionDb.add(userA, "Vraag C");
        Question questionD = this.questionDb.add(userB, "Vraag D");
        
        List<Question> questionsA = this.questionDb.getByUser(userA);
        List<Question> questionsB = this.questionDb.getByUser(userB);
        List<Question> questionsC = this.questionDb.getByUser(userC);

        assertEquals(3, questionsA.size());
        assertEquals(1, questionsB.size());
        assertEquals((int)0, questionsC.size());
       
        assertTrue(questionsA.contains(questionA));
        assertTrue(questionsA.contains(questionB));
        assertTrue(questionsA.contains(questionC));
        assertTrue(questionsB.contains(questionD));

    }
    
    @Test
    public void test_it_gets_specific_questions_from_a_user() throws DatabaseException {
        User userA = this.activatedUserA;
        User userB = this.activatedUserB;
        
        Question questionA = this.questionDb.add(userA, "Vraag A");
        Question questionB = this.questionDb.add(userA, "Vraag B");
                 questionB.disapprove();                            // Weigeren
        Question questionC = this.questionDb.add(userA, "Vraag B"); // Dezelfde vraag, later tijdstip
        Question questionD = this.questionDb.add(userB, "Vraag D");
        
        List<Question> questionsA = this.questionDb.getByUser(userA, "Vraag B");
        List<Question> questionsB = this.questionDb.getByUser(userB, "Vraag D");
        
        assertEquals(2, questionsA.size());
        assertEquals(1, questionsB.size());
        
        assertTrue(questionsA.contains(questionB));
        assertTrue(questionsA.contains(questionC));
        assertTrue(questionsB.contains(questionD));
    }
    

}
