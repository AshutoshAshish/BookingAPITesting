package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	public static Properties prop;
	
	public ConfigReader() {
		try {
			prop= new Properties();
			FileReader fr= new FileReader(FilePaths.BASE_PATH+"config.properties");
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getFirstName() {
		return prop.getProperty("firstname");
	}

}
