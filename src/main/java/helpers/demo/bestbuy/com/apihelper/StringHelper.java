package demo.bestbuy.com.apihelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {

	private String _stringToReplace;

	public StringHelper(String stringToReplace) {
		_stringToReplace = stringToReplace;

	}

	public String getModifiedDateString() {
		return _stringToReplace.replace(" +00:00", "Z").replace(" ", "T");
	}

	public static String requestedParsedString(String inputString) {
		switch (inputString) {
		case "NULL":
			return null;
		case "EMPTY":
			return "";
		default:
			return inputString;
		}
	}

	public static String getParsedDate(String date) {
		Date datePared = null;
		try {
			date = date.replace("T", " ").replace("Z", "");
			datePared = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return DateFormat.getDateInstance().format(datePared);
	}
}
