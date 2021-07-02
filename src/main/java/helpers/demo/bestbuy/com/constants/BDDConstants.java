package demo.bestbuy.com.constants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains the BDD Constants
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class BDDConstants {

	private static String getPropertiesFileValue(String key) {
		FileReader reader = null;
		try {
			reader = new FileReader(
					System.getProperty("user.dir") + "/src/main/resources/Configuration/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}

	/**
	 * Return the value of database string from properties file.
	 * 
	 * @return : DataBase string value.
	 * @author Lavendra rajput
	 */
	public static String getDataBaseConnectionString() {
		return getPropertiesFileValue("sqldbconnection");
	}

	/**
	 * Return the value of baseuri from properties file.
	 * 
	 * @return : Base Uri
	 * @author Lavendra rajput
	 */
	public static String getBaseUri() {
		return getPropertiesFileValue("baseuri");
	}

}
