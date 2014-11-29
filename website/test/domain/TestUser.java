package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUser {

    @Test
    public void test_it_can_be_created() throws DomainException {
        int id = 1337;
        String naam = "Voornaam Achternaam";
        String email = "voornaam.achternaam@student.khleuven.be";
        AccesLevel accesLevel = AccesLevel.USER_CREATED;
        boolean removed = false;

        User user = this.createUser(id, naam, email, accesLevel, removed);

        assertNotNull(user);

        assertEquals(id, user.getId());
        assertEquals(naam, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(accesLevel, user.getLevel());
        assertEquals(removed, user.isRemoved());
    }

    @Test(expected = DomainException.class)
    public void test_it_requires_a_name() throws DomainException {
        this.createUser(1, "", "test@test.be", AccesLevel.USER_CREATED, false);
    }

    @Test(expected = DomainException.class)
    public void test_it_requires_an_accesslevel() throws DomainException {
        this.createUser(1, "Naam", "test@test.be", null, false);
    }

    @Test(expected = DomainException.class)
    public void test_it_requires_correct_email() throws DomainException {
        this.createUser(1, "Naam", "invalidemail.be", AccesLevel.USER_CREATED,
                false);

    }

    @Test
    public void test_it_has_different_acceslevels() throws DomainException {
        User user = this.createUser(1, "Naam", "test@test.be",AccesLevel.USER_CREATED, false);

        assertTrue(user.isUser());
        assertFalse(user.isAdmin());
        assertFalse(user.isBlocked());

        user.setAccesLevel(AccesLevel.ADMIN);

        assertFalse(user.isUser());
        assertTrue(user.isAdmin());
        assertFalse(user.isBlocked());
    }

    private User createUser(int id, String name, String email,AccesLevel level, boolean removed)
            throws DomainException {
        return new User(id, name, email, level, removed);
    }

}
