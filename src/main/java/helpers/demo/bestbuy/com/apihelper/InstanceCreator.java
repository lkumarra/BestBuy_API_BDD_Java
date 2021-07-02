package demo.bestbuy.com.apihelper;

import demo.bestbut.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.categories.GetCategories;
import demo.bestbuy.com.products.GetProductViaId;
import demo.bestbuy.com.products.GetProducts;
import demo.bestbuy.com.restassuredhelper.RestAssuredHelper;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;

/**
 * This class is used to create the Instance of classes
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class InstanceCreator {

	private static GetCategories getCategories = null;
	private static GetProducts getProducts = null;
	private static ResponseModalWrapper responseModalWrapper = null;
	private static GetProductViaId getProductViaId = null;
	private static RestAssuredHelper restAssuredHelper = null;
	private static ResponseValidator responseValidator = null;

	/**
	 * Create the instance of GetCategories class
	 * 
	 * @param responseValidator {@link ResponseValidator}
	 * @return {@link GetCategories}
	 * @author Lavendra rajput
	 */
	public static GetCategories getCategoriesInstance(ResponseValidator responseValidator) {
		if (getCategories == null) {
			getCategories = new GetCategories(responseValidator);
		}
		return getCategories;
	}

	/**
	 * Create the instance of GetProducts class
	 * 
	 * @param responseValidator {@link ResponseValidator}
	 * @return {@link GetProducts}
	 * @author Lavendra rajput
	 */
	public static GetProducts getProdcutsInstance(ResponseValidator responseValidator) {
		if (getProducts == null) {
			getProducts = new GetProducts(responseValidator);
		}
		return getProducts;
	}

	/**
	 * Create the Instance of ResponseModalWrapper class
	 * 
	 * @return {@link ResponseModalWrapper}
	 * @author Lavendra rajput
	 */
	public static ResponseModalWrapper getResponseModalWrapperInstance() {
		if (responseModalWrapper == null) {
			responseModalWrapper = new ResponseModalWrapper();
		}
		return responseModalWrapper;
	}

	/**
	 * Create the Instance of GetProductViaId class
	 * 
	 * @param responseValidator : {@link ResponseValidator}
	 * @return {@link GetProductViaId}
	 * @author Lavendra rajput
	 */
	public static GetProductViaId getProductViaIdInstance(ResponseValidator responseValidator) {
		if (getProductViaId == null) {
			getProductViaId = new GetProductViaId(responseValidator);
		}
		return getProductViaId;
	}

	/**
	 * Create the instace of RestAssuredHelper class
	 * 
	 * @return {@link RestAssuredHelper}
	 * @author Lavendra rajput
	 */
	public static RestAssuredHelper getRestAssuredHelperInstace() {
		if (restAssuredHelper == null) {
			restAssuredHelper = new RestAssuredHelper();
		}
		return restAssuredHelper;
	}

	/**
	 * Create the instance of ResponseValidator Class
	 * 
	 * @return {@link ResponseValidator}
	 * @author Lavendra rajput
	 */
	public static ResponseValidator getResponseValidatorInstace() {
		if (responseValidator == null) {
			responseValidator = new ResponseValidator();
		}
		return responseValidator;
	}
}
