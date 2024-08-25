package demo.bestbuy.com.helpers.apihelper;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public final class StringHelper {

    private final String _stringToReplace;

    public StringHelper(String stringToReplace) {
        _stringToReplace = stringToReplace;

    }

    public String getModifiedDateString() {
        return _stringToReplace.replace(" +00:00", "Z").replace(" ", "T");
    }

    public String getModifiedDateString(String dateToModify) {
        return dateToModify.replace(" +00:00", "Z").replace(" ", "T");
    }

    public static String getRequestedParsedString(String inputString) {
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
            log.error("Error while parsing the date with error message : {}", e.getMessage());
        }
        return DateFormat.getDateInstance().format(datePared);
    }
}
