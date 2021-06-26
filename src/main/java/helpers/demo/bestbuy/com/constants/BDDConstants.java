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

	public static String dataBaseConnString = getPropertiesFileValue("sqldbconnection");
	public static String baseUri = getPropertiesFileValue("baseuri");
	public static RestAssuredHelper restAssuredHelper = new RestAssuredHelper();
}
