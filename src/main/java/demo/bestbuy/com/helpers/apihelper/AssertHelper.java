package demo.bestbuy.com.helpers.apihelper;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public final class AssertHelper {

    public static void AssertFail(Object actual, Object expected) {
        Assert.fail("Actual response is :- " + "\n" + serializeObject(actual) + "\n" + " But expected is :- " + "\n" + serializeObject(expected));
    }

    private static String serializeObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String serializedObject = null;
        try {
            serializedObject = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error while parsing the JSON with error message : {}", e.getMessage());
        }
        return serializedObject;
    }
}
