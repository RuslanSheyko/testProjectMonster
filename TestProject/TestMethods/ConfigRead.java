package TestMethods;

import java.io.*;
import java.util.Properties;

public class ConfigRead {

    private static String configRead(String key, String url) {
        Properties prop = new Properties();
        InputStream input = null;
        String value;

        try {
            input = new FileInputStream(url);
            prop.load(input);
            // set the properties value
            value = prop.getProperty(key);
        } catch (IOException io) {
            io.printStackTrace();
            value = null;
        } finally {
            if (input != null) try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return value;
    }

    public static String readBaseConfig(String key) {
        return configRead(key, "base");
    }

}
