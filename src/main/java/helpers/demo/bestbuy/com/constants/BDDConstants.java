package demo.bestbuy.com.constants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import demo.bestbuy.com.restassuredhelper.RestAssuredHelper;

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
	 * @return : DataBase string value.
	 */
	public static String getDataBaseConnectionString() {
		return getPropertiesFileValue("sqldbconnection");
	}

	/**
	 * Return the value of baseuri from properties file.
	 * @return : Base Uri
	 */
	public static String getBaseUri() {
		return getPropertiesFileValue("baseuri");
	}
	
	/**
	 * Return the object of RestAssuredHelper class.
	 * @return : Object of RestAssuredHelper class
	 */
	public static RestAssuredHelper getResassuredHelper() {
		return new RestAssuredHelper();
	}
	
}
