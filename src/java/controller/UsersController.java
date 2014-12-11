package controller;

import javax.servlet.ServletException;

import database.UserDatabase;
import framework.Container;
import framework.http.controllers.ResourceController;

public class UsersController extends ResourceController{
    
    protected UserDatabase userDb;

    public UsersController(Container app) {
        super(app);
        this.userDb = (UserDatabase) app.make("UserDatabase");
    }

    /**
     * Toon een overzicht van de entiteiten
     * 
     */
    @Override
    public void index() throws ServletException {
        
        this.dispatch("users/index.jsp");
    }

    /**
     * Toon een formulier om een nieuwe entiteit te maken
     * 
     */
    @Override
    public void create() throws ServletException {
        
    }

    /**
     * Sla een nieuwe entiteit op
     * 
     */
    @Override
    public void store() throws ServletException {
        
    }

    /**
     * Toon een entiteit
     * 
     */
    @Override
    public void show() throws ServletException {
        
    }

    /**
     * Toon een formulier om een entiteit te wijzigen
     * 
     */
    @Override
    public void edit() throws ServletException {
        
    }

    /**
     * Update een entiteit
     * 
     */
    @Override
    public void update() throws ServletException {
        
    }

    /**
     * Delete een entiteit
     * 
     */
    @Override
    public void delete() throws ServletException {
        
    }
    
    
}
