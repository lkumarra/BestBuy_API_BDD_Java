package demo.bestbuy.com.helpers.interfaces;

import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import demo.bestbuy.com.wrapper.ResponseWrapper;

public interface IResponseValidator {

	void setResponseWrapper(ResponseWrapper responseWrapper);
	void verifyResponse(ResponseModalWrapper responseModalWrapper);
}
