package demo.bestbuy.com.responsevalidator;

import org.testng.Assert;

import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import demo.bestbuy.com.wrapper.ResponseWrapper;

/**
 * This class contains the methods related to response validation
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date
 */
public class ResponseValidator implements IResponseValidator {

	private static ResponseWrapper responseWrapper;

	public ResponseWrapper getResponseWrapper() {
		return responseWrapper;
	}

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
		if (getResponseWrapper().getResponse().contains("name")
				&& getResponseWrapper().getResponse().contains("message")
				&& getResponseWrapper().getResponse().contains("errors")
				&& getResponseWrapper().getResponse().contains("data")) {
			if (!JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "name")
					.equals(responseModalWrapper.getName())) {
				Assert.fail("Actaul name is "
						+ JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "name")
						+ " Expected name is " + responseModalWrapper.getName());
			}
			if (!JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "message")
					.equals(responseModalWrapper.getMessage())) {
				Assert.fail("Actaul message is "
						+ JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "message")
						+ " Expected message is " + responseModalWrapper.getMessage());
			}
			if (!JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "errors")
					.equals(responseModalWrapper.getErrors())) {
				Assert.fail("Actaul error is "
						+ JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "errors")
						+ " Expected error is " + responseModalWrapper.getErrors());
			}

		}
		if (getResponseWrapper().getResponse().contains("name")
				&& getResponseWrapper().getResponse().contains("message")
				&& getResponseWrapper().getResponse().contains("errors")) {
			if (!JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "name")
					.equals(responseModalWrapper.getName())) {
				Assert.fail("Actaul name is "
						+ JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "name")
						+ " Expected name is " + responseModalWrapper.getName());
			}
			if (!JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "message")
					.equals(responseModalWrapper.getMessage())) {
				Assert.fail("Actaul message is "
						+ JsonParserHelper.getParsedValueFromJson(getResponseWrapper().getResponse(), "message")
						+ " Expected message is " + responseModalWrapper.getMessage());
			}
		}
	}
}
