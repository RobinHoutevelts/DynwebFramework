package service;

import java.util.HashMap;

/**
 * Inversion of Control Container
 * 
 * Wordt gebruikt om dependencies doorheen de applicatie te centraliseren.
 *
 */
public class IoC {

    /**
     * Hashmap met de binding
     */
    private HashMap<String, IoCResolver> bindings = new HashMap<String, IoCResolver>();

    /**
     * Voeg een binding toe aan de IoC container
     * 
     * @param name
     * @param resolver
     */
    public void bind(String name, IoCResolver resolver) {
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
        IoCResolver resolver = this.bindings.get(name);

        return resolver.resolve();
    }
}
