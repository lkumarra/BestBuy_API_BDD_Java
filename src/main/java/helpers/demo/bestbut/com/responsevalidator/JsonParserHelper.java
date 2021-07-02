package demo.bestbut.com.responsevalidator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class contains the method related to JSON Parsing
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class JsonParserHelper {

	/**
	 * Parsed the Json and get the value
	 * 
	 * @param json   :JSON to parse
	 * @param object : Object name to fetch value
	 * @return Value of fetched object
	 * @author Lavendra rajput
	 */
	public static String getParsedValueFromJson(String json, String object) {
		JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
		return jsonObject.get(object).getAsString();
	}

}
