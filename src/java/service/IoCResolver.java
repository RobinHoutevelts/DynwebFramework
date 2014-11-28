package service;

/**
 * Resolver voor de IoC container. Is verantwoordelijk voor het aanmaken van een
 * concreet object.
 *
 */
public interface IoCResolver {

    /**
     * Genereer een object
     * @return
     */
    public Object resolve();

}
