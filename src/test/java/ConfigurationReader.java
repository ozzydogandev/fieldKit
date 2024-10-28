import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static final Properties properties = new Properties();

    static {
        try {

            FileInputStream fileInputStream = new FileInputStream("configuration.properties"); // Reads file in java. We need to pass the path of the file.
            properties.load(fileInputStream); // Load contents of the file the properties object.
            fileInputStream.close();

        } catch (IOException e) {  // Catches any IO exception if the file cannot be read.
            e.printStackTrace();  // Prints the stack trace for debugging if an error occurs.
        }
    }

    // Method to retrieve the value associated with a specific key in the properties file.
    public static String getProperty(String key){
        return properties.getProperty(key); // Returns the value for the given key, or null if not found.
    }
}
