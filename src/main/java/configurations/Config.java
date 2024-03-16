package configurations;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
public class Config {

    private Properties prop = new Properties();
    private FileInputStream input = null;
    private FileWriter fw = null ;

    public Config() {
        try {

            input = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/java/configuration/config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Config(String fileName) {
        try {

            input = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/java/configuration/" + fileName +".properties");
            fw = new FileWriter(System.getProperty("user.dir") + "/src/main/java/configuration/" + fileName +".properties", true) ;

            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String Key) {
        return prop.getProperty(Key);
    }

    public synchronized void setProperty(String key, String value) {
        Properties prop  = new Properties();
        prop.setProperty(key, value ) ;
        try {

            prop.store(fw, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
