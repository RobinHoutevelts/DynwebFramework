package database;

import domain.AccesLevel;
import domain.User;

import java.util.Collection;

public interface UserDatabase {

    /**
     * Voegt een gebruiker toe aan de databank en instantieert deze.
     * 
     * @param name
     * @param email
     * @param password
     * @param level
     * @return
     * @throws DatabaseException
     */
    public User add(String name, String email, String password, AccesLevel level)
            throws DatabaseException;
    
    /**
     * Verwijderd een gebruiker uit de databank
     * 
     * @param user
     * @return
     * @throws DatabaseException
     */
    public void remove(User user) throws DatabaseException;

    /**
     * Schrijft eventuele wijzigingen weg naar de databank.
     * 
     * @param user
     * @return
     * @throws DatabaseException
     */
    public void update(User user) throws DatabaseException;

    /**
     * Instantieert een gebruiker op basis van zijn Id
     * 
     * @param id
     * @return
     * @throws DatabaseException
     */
    public User get(long id) throws DatabaseException;
    
    /**
     * Instantieert een gebruiker op basis van zijn e-mail
     * 
     * @param email
     * @return
     * @throws DatabaseException
     */
    public User getByEmail(String email) throws DatabaseException;

    /**
     * Instantieert een gebruiker op basis van de combinatie e-mail en wachtwoord
     * Gebruikt voor login
     * 
     * @param email
     * @param password
     * @return
     * @throws DatabaseException
     */
    public User getByCredentials(String email, String password) throws DatabaseException;
    
    public Collection<User> getAll();


}