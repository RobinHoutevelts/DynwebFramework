package framework;

import java.util.HashMap;

import javax.servlet.ServletContext;

import framework.service.Resolver;

/**
 * Inversion of Control Container
 * 
 * Wordt gebruikt om dependencies doorheen de applicatie te centraliseren.
 *
 */
public class Container {

    /**
     * Hashmap met de bindings
     */
    private HashMap<String, Resolver> bindings = new HashMap<String, Resolver>();
    private ServletContext context;

    /**
     * Voeg een binding toe aan de IoC container
     * 
     * @param name
     * @param resolver
     */
    public void bind(String name, Resolver resolver) {
        this.bindings.put(name, resolver);
    }

    /**
     * Maak een instantie vanuit de IoC container
     * 
     * @param name
     * @return
     */
    public Object make(String name) {
        // Zoek de bijhorende resolver
        Resolver resolver = this.bindings.get(name);

        return resolver.resolve();
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }
    
    public ServletContext getContext()
    {
        return this.context;
    }
}
