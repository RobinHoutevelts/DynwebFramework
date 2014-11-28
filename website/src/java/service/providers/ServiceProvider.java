package service.providers;

import service.IoC;

/**
 * ServiceProviders zijn de backbone van de IoC container. Ze is
 * verantwoordelijk voor het leveren van Resolvers.
 *
 */
public interface ServiceProvider {

    /**
     * Registreer je ServiceProvider. Voeg eventueel bindings toe aan de IoC
     * container of plaats listeners, overschrijf bindings, whatever
     * 
     * @param app
     */
    public void register(IoC app);

}
