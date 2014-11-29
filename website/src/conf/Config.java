package conf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Config {

    static private Configuration config = new PropertiesConfiguration();

    /**
     * Haalt een configwaarde uit de map src/conf
     * 
     * @param configName
     * @return
     */
    static public String get(String name) {
        return config.getString(name);
    }
    
    static public List<Object> getList(String name) {
        return config.getList(name);
    }

    static public void set(String name, String value) {
        config.setProperty(name, value);
    }

    /**
     * Laad alle configuratiebestanden in
     */
    static public void load() {
        // Is de config al ingelezen?
        if (!config.isEmpty())
            return;

        InputStream configDir = Config.class.getResourceAsStream("/conf");
                
        BufferedReader dirContentReader = new BufferedReader(new InputStreamReader(configDir));

        String filename;
        String extension;
        int i;
        try {
            while ((filename = dirContentReader.readLine()) != null) {
                extension = "";

                i = filename.lastIndexOf('.');
                if (i > 0) {
                    extension = filename.substring(i + 1);
                }

                if (extension.equals("properties")) {
                    load("conf/" + filename);
                }
            }
            dirContentReader.close();
        } catch (IOException | ConfigurationException e) {
            // TODO: exception handling
            e.printStackTrace();
        }

    }

    /**
     * Laad een enkel configuratiebestand in.
     * 
     * @param configFile
     * @throws IOException
     * @throws ConfigurationException
     */
    static private void load(String configFile) throws ConfigurationException {
        Configuration properties = new PropertiesConfiguration(configFile);

        Iterator<String> keyIterator = properties.getKeys();

        // Alle waarden van het bestand kopieren naar de statische config
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();

            Object property = properties.getProperty(key);
            config.setProperty(key, property);
        }
    }

}
