package database;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.AccesLevel;
import domain.User;
import framework.config.Config;
import framework.database.Database;

public class TestJDBCUserDatabase {

    private Database db;
    private UserDatabase userDb;

    @Before
    public void setUp() throws Exception {
        Config.load();
        
        String dbDriver = Config.get("dbDriver");
        String dbConnectionUrl = Config.get("dbConnectionUrl");
        String dbUsername = Config.get("dbUsername");
        String dbPassword = Config.get("dbPassword");

        this.db = new Database(dbDriver, dbConnectionUrl, dbUsername,dbPassword);

        this.userDb = new JDBCUserDatabase(this.db);
    }

    @After
    public void tearDown() throws Exception {

        // Tabel leegmaken
        this.db.prepareStatement("TRUNCATE Users").execute();
        
        this.db.closeConnection();
    }

    @Test
    public void test_it_adds_users() throws DatabaseException {
        String naam = "Voornaam Achternaam";
        String email = "voornaam.achternaam@student.khleuven.be";
        String wachtwoord = "wachtwoord";
        AccesLevel level = AccesLevel.USER_CREATED;

        User user = this.userDb.add(naam,email,wachtwoord,level);
        
        assertNotNull(user);
        assertEquals(1, user.getId()); // Is de eerste user aangemaakt
        assertEquals(naam, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(level, user.getLevel());
        
        // 2e gebruiker maken.
        naam = "Achternaam Voornaam";
        email = "achternaam.voornaam@student.khleuven.be";
        wachtwoord = "wachtwoord";
        level = AccesLevel.USER_CREATED;
        
        user = this.userDb.add(naam,email,wachtwoord,level);
        
        assertNotNull(user);
        assertEquals(2, user.getId()); // Is de tweede user aangemaakt
        assertEquals(naam, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(level, user.getLevel());
    }
    
    @Test(expected=DatabaseException.class)
    public void test_it_removes_users() throws DatabaseException{
        String naam = "Voornaam Achternaam";
        String email = "voornaam.achternaam@student.khleuven.be";
        String wachtwoord = "wachtwoord";
        AccesLevel level = AccesLevel.USER_CREATED;

        User user = this.userDb.add(naam,email,wachtwoord,level);
        
        this.userDb.remove(user);
        
        assertTrue(user.isRemoved());
        
        // Should throw exception because user is deleted
        this.userDb.get(user.getId());
    }
    
    @Test
    public void test_it_updates_users() throws DatabaseException{
        String naam = "Voornaam Achternaam";
        String email = "voornaam.achternaam@student.khleuven.be";
        String wachtwoord = "wachtwoord";
        AccesLevel level = AccesLevel.USER_CREATED;

        User user = this.userDb.add(naam,email,wachtwoord,level);
        
        // We krijgen een promotie!
        
        naam = "Dr. Voornaam Achternaam";
        email = "andere.email@student.kuleuven.be";
        level = AccesLevel.ADMIN;
        
        // Wijzigingen wegschrijven naar de databank
        this.userDb.update(user);
        
        // Gebruiker opnieuw ophalen vanuit de databank
        User userB = this.userDb.get(user.getId());
        
        // Gegevens zouden exact hetzelfde moeten zijn.
        assertEquals(user, userB);
    }
    
    @Test(expected=DatabaseException.class)
    public void test_it_throws_exception_when_email_is_already_used() throws DatabaseException{
        String naam = "Voornaam Achternaam";
        String email = "voornaam.achternaam@student.khleuven.be";
        String wachtwoord = "wachtwoord";
        AccesLevel level = AccesLevel.USER_CREATED;
        this.userDb.add(naam,email,wachtwoord,level);
        
        // En nog eens
        this.userDb.add(naam,email,wachtwoord,level);
    }
    
    @Test
    public void test_it_gets_a_user_by_id() throws DatabaseException{
        String naam = "Voornaam Achternaam";
        String email = "voornaam.achternaam@student.khleuven.be";
        String wachtwoord = "wachtwoord";
        AccesLevel level = AccesLevel.USER_CREATED;
        User userA = this.userDb.add(naam,email,wachtwoord,level);
        User userB = this.userDb.get(userA.getId());
        
        assertEquals(userA, userB);
    }
    
    @Test
    public void test_it_gets_a_user_by_email() throws DatabaseException{
        String naam = "Voornaam Achternaam";
        String email = "voornaam.achternaam@student.khleuven.be";
        String wachtwoord = "wachtwoord";
        AccesLevel level = AccesLevel.USER_CREATED;
        User userA = this.userDb.add(naam,email,wachtwoord,level);
        User userB = this.userDb.getByEmail(email);
        
        assertEquals(userA, userB);
    }
    
    @Test
    public void test_it_gets_a_user_by_credentials() throws DatabaseException{
        String naam = "Voornaam Achternaam";
        String email = "voornaam.achternaam@student.khleuven.be";
        String wachtwoord = "wachtwoord";
        AccesLevel level = AccesLevel.USER_CREATED;
        User userA = this.userDb.add(naam,email,wachtwoord,level);
        User userB = this.userDb.getByCredentials(email, wachtwoord);
        
        assertEquals(userA, userB);
    }

}
