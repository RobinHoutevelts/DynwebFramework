package database;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import conf.Config;
import domain.AccesLevel;
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
        blockedUser = userDb.add("Ruben Moermans", "ruben.moermans@student.khleuven.be", "passwordHash", AccesLevel.BLOCKED);
        createdUser = userDb.add("Fery Vousure", "fery.vousure@student.khleuven.be", "passwordHash", AccesLevel.USER_CREATED);
        activatedUserA = userDb.add("Kevin Peeters", "kevin.peeters3@student.khleuven.be", "passwordHash", AccesLevel.USER_ACTIVATED);
        activatedUserB = userDb.add("Kevin Peeters", "kevin.peeters3@student.khleuven.be", "passwordHash", AccesLevel.USER_ACTIVATED);
        activatedUserC = userDb.add("Kevin Peeters", "kevin.peeters3@student.khleuven.be", "passwordHash", AccesLevel.USER_ACTIVATED);
    }

    @After
    public void tearDown() throws Exception {

        // Tabel leegmaken
        this.db.prepareStatement("TRUNCATE Questions").execute();
        this.db.prepareStatement("TRUNCATE Users").execute();
        
        
        this.db.closeConnection();
    }
    
    @Test
    public void test_it_can_add_questions() {
        fail("Not yet implemented");
    }
    
    @Test
    public void test_it_removes_questions() {
        fail("Not yet implemented");
    }
    
    @Test
    public void test_it_gets_a_question_by_id() {
        fail("Not yet implemented");
    }
    
    @Test
    public void test_it_updates_a_question() {
        fail("Not yet implemented");
    }
    
    @Test
    public void test_it_gets_questions_from_a_user() {
        fail("Not yet implemented");
    }
    
    @Test
    public void test_it_gets_specific_questions_from_a_user() {
        fail("Not yet implemented");
    }
    

}
