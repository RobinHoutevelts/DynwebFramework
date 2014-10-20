package conf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config {

    static private Properties config = new Properties();
    static final private ClassLoader loader = Config.class.getClassLoader();

    /**
     * Haalt een configwaarde uit de map src/conf
     * 
     * @param configName
     * @return
     */
    static public String get(String name)
    {
        return config.getProperty(name);
    }

    static public void set(String name, String value)
    {
        config.setProperty(name, value);
    }

    /**
     * Laad alle configuratiebestanden in
     */
    static public void load()
    {
        // Is de config al ingelezen?
        if(config.size() > 0)
            return;

        InputStream configDir = loader.getResourceAsStream("/conf");
        BufferedReader dirContentReader = new BufferedReader(new InputStreamReader(configDir));

        String filename;
        String extension;
        int i;
        try {
            while ((filename = dirContentReader.readLine()) != null) {
                extension = "";

                i = filename.lastIndexOf('.');
                if (i > 0) {
                    extension = filename.substring(i+1);
                }

                if (extension.equals("properties")) {
                    load("conf/"+filename);
                }
            }
            dirContentReader.close();
        } catch (IOException e) {
            // TODO: exception handling
            e.printStackTrace();
        }

    }

    /**
     * Laad een enkel configuratiebestand in.
     * 
     * @param configFile
     * @throws IOException
     */
    static private void load(String configFile) throws IOException
    {
        Properties properties = new Properties();
        properties.load(loader.getResourceAsStream(configFile));

        config.putAll(properties);
    }

}
