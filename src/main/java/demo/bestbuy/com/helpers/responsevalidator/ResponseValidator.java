package demo.bestbuy.com.helpers.responsevalidator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;

import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import demo.bestbuy.com.wrapper.ResponseWrapper;

/**
 * This class contains the methods related to response validation
 *
 * @author Lavendra Kumar Rajput
 * @Date
 */
public final class ResponseValidator implements IResponseValidator {

    @Getter
    private static ResponseWrapper responseWrapper;


    @Override
    public void setResponseWrapper(ResponseWrapper responseWrapper) {
        ResponseValidator.responseWrapper = responseWrapper;
    }

    /**
     * Verify the Response returned
     *
     * @author Lavendra rajput
     */
    public void verifyResponse(ResponseModalWrapper responseModalWrapper) {
        if (getResponseWrapper().getStatusCode() != responseModalWrapper.getCode()) {
            Assert.fail("Expected status code is " + responseModalWrapper.getCode() + " But actual Status code is "
                    + getResponseWrapper().getStatusCode());
        }
        if (ifContainsNameMessageErrorsAndData()) {
            verifyJsonField(getResponseWrapper().getResponse(), "name", responseModalWrapper.getName());
            verifyJsonField(getResponseWrapper().getResponse(), "message", responseModalWrapper.getMessage());
            verifyJsonField(getResponseWrapper().getResponse(), "errors", responseModalWrapper.getErrors());

        }
        if (ifContainsNameMessageAndErrors()) {
            verifyJsonField(getResponseWrapper().getResponse(), "name", responseModalWrapper.getName());
            verifyJsonField(getResponseWrapper().getResponse(), "message", responseModalWrapper.getMessage());
        }
    }

    private void verifyJsonField(String response, String fieldName, String expectedValue) {
        if (response.contains(fieldName)) {
            String actualValue = JsonParserHelper.getParsedValueFromJson(response, fieldName);
            if (!actualValue.equals(expectedValue)) {
                Assert.fail("Actual " + fieldName + " is " + actualValue +
                        " Expected " + fieldName + " is " + expectedValue);
            }
        }
    }

    private boolean ifContainsNameMessageErrorsAndData() {
        return getResponseWrapper().getResponse().contains("name")
                && getResponseWrapper().getResponse().contains("message")
                && getResponseWrapper().getResponse().contains("errors")
                && getResponseWrapper().getResponse().contains("data");
    }

    private boolean ifContainsNameMessageAndErrors() {
        return getResponseWrapper().getResponse().contains("name")
                && getResponseWrapper().getResponse().contains("message")
                && getResponseWrapper().getResponse().contains("errors");
    }
}
