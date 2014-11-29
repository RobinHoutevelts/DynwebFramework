package framework;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import conf.Config;
import framework.service.providers.ServiceProvider;

public class Bootstrapper {
    
    private Container app;
    
    public Bootstrapper(Container app){
        this.app = app;
    }
    
    /**
     * Het opstarten van ons framework.
     */
    public void initialize() {
        // Laad configuratiebestand
        Config.load();
        
        // Verzamel alle serviceproviders
        List<ServiceProvider> providers = this.getProviders();
        
        // Registreer de serviceproviders
        this.registerProviders(providers);
    }
    
    /**
     * 
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<ServiceProvider> getProviders() {
        
        List<ServiceProvider> providers = new ArrayList<ServiceProvider>();

        // Haal lijst van serviceproviders uit configuratiebestand
        List<Object> providerClasses = Config.getList("serviceproviders.provider");
        
        // Intantieer alle serviceproviders
        for (Object OclassName : providerClasses) {
            String className = (String) OclassName;
            ServiceProvider serviceProvider = null;
            
            try {
                Class serviceProviderClass = Class.forName(className);
                Constructor constructor = serviceProviderClass.getConstructor();
                serviceProvider = (ServiceProvider) constructor.newInstance();
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Er is iets foutgelopen bij het initialiseren van de serviceproviders",e);
            }

            if (serviceProvider == null)
                continue;

            providers.add(serviceProvider);
        }
        return providers;
    }
    
    public void registerProviders(List<ServiceProvider> providers) {
        // Register serviceproviders
        for (ServiceProvider provider : providers) {
            provider.register(this.app);
            
            Logger.getLogger(provider.getClass().getName()).log(Level.INFO, "ServiceProvider '"+provider.getClass().getName()+"' werd geregistreerd.");
        }
    }

}
