package demo.bestbut.com.responsevalidator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserHelper {

	/**
	 * Parsed the Json and get the value
	 * @param json :JSON to parse
	 * @param object : Object name to fetch value
	 * @return Value of fetched object
	 */
	public static String getParsedValueFromJson(String json, String object) {
		JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
		return jsonObject.get(object).getAsString();
	}

}
