package demo.bestbut.com.responsevalidator;

import org.testng.Assert;

import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import demo.bestbuy.com.wrapper.ResponseWrapper;

public class ResponseValidator implements IResponseValidator {

	private static ResponseWrapper responseWrapper;

	public ResponseWrapper getResponseWrapper() {
		return responseWrapper;
	}

	public void setResponseWrapper(ResponseWrapper responseWrapper) {
		ResponseValidator.responseWrapper = responseWrapper;
	}

	public void verifyResponse(ResponseModalWrapper responseModalWrapper) {
		if (getResponseWrapper().getStatusCode() != responseModalWrapper.getCode()) {
			Assert.fail("Expected status code is " + responseModalWrapper.getCode() + " But acual Status code is "
					+ getResponseWrapper().getStatusCode());
		}
		if (getResponseWrapper().getResponse().contains("name")
				&& getResponseWrapper().getResponse().contains("message")
				&& getResponseWrapper().getResponse().contains("errors")
				&& getResponseWrapper().getResponse().contains("data")) {

		}
		if (getResponseWrapper().getResponse().contains("name")
				&& getResponseWrapper().getResponse().contains("message")) {

		}
	}
}
