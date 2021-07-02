package demo.bestbuy.com.baseapi;

import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import demo.bestbuy.com.wrapper.ResponseWrapper;

/**
 * This is BASE class of all API Sub classed
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date
 */
public class BaseAPI {

	protected ResponseWrapper responseWrapper;
	protected IResponseValidator responseValidator;
	protected String endPoint;

	public BaseAPI(String endPoint, IResponseValidator responseValidator) {
		this.endPoint = endPoint;
		this.responseValidator = responseValidator;
	}

	/**
	 * Verify the response returned
	 * 
	 * @param responseModalWrapper : ResponseModalWrapper
	 */
	public void VerifyResponse(ResponseModalWrapper responseModalWrapper) {
		this.responseValidator.verifyResponse(responseModalWrapper);
	}

}
