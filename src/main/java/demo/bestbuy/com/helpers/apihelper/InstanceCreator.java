package demo.bestbuy.com.helpers.apihelper;

import demo.bestbuy.com.categories.GetCategories;
import demo.bestbuy.com.categories.GetCategoriesViaId;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.restassuredhelper.RestAssuredHelper;
import demo.bestbuy.com.products.AddProducts;
import demo.bestbuy.com.products.GetProductViaId;
import demo.bestbuy.com.products.GetProducts;
import demo.bestbuy.com.services.GetServices;
import demo.bestbuy.com.services.GetServicesViaId;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import demo.bestbuy.com.wrapper.ResponseWrapper;

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
	private static IResponseValidator responseValidator = null;
	private static GetCategoriesViaId getCategoriesViaId = null;
	private static GetServices getServices = null;
	private static GetServicesViaId getServicesViaId = null;
	private static AddProducts addProducts = null;

	/**
	 * Create the instance of {@link GetCategories}
	 * 
	 * @param responseValidator {@link ResponseValidator}
	 * @return {@link GetCategories}
	 * @author Lavendra rajput
	 */
	public static GetCategories getCategoriesInstance(IResponseValidator responseValidator) {
		if (getCategories == null) {
			getCategories = new GetCategories(responseValidator);
		}
		return getCategories;
	}

	/**
	 * Create the instance of {@link GetProducts}
	 * 
	 * @param responseValidator {@link ResponseValidator}
	 * @return {@link GetProducts}
	 * @author Lavendra rajput
	 */
	public static GetProducts getProdcutsInstance(IResponseValidator responseValidator) {
		if (getProducts == null) {
			getProducts = new GetProducts(responseValidator);
		}
		return getProducts;
	}

	/**
	 * Create the Instance of {@link ResponseWrapper}
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
	 * Create the Instance of {@link GetProductViaId}
	 * 
	 * @param responseValidator : {@link ResponseValidator}
	 * @return {@link GetProductViaId}
	 * @author Lavendra rajput
	 */
	public static GetProductViaId getProductViaIdInstance(IResponseValidator responseValidator) {
		if (getProductViaId == null) {
			getProductViaId = new GetProductViaId(responseValidator);
		}
		return getProductViaId;
	}

	/**
	 * Create the instace of {@link RestAssuredHelper}
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
	 * Create the instance of {@link ResponseValidator}
	 * 
	 * @return {@link ResponseValidator}
	 * @author Lavendra rajput
	 */
	public static IResponseValidator getResponseValidatorInstace(IResponseValidator iresponseValidator) {
		responseValidator = iresponseValidator;
		return responseValidator;
	}

	/**
	 * Create the instance of {@link GetCategoriesViaId}
	 * 
	 * @param responseValidator {@link ResponseValidator}
	 * @return {@link GetCategoriesViaId}
	 */
	public static GetCategoriesViaId getCategoriesViaIdInstance(IResponseValidator responseValidator) {
		if (getCategoriesViaId == null) {
			getCategoriesViaId = new GetCategoriesViaId(responseValidator);
		}
		return getCategoriesViaId;
	}

	/**
	 * Return the instance of {@link GetServices}
	 * 
	 * @param responseValidator {@link IResponseValidator}
	 * @return {@link GetServices}
	 */
	public static GetServices getSevicesInstance(IResponseValidator responseValidator) {
		if (getServices == null) {
			getServices = new GetServices(responseValidator);
		}
		return getServices;
	}

	/**
	 * Return the instance of {@link GetServicesViaId}
	 * 
	 * @param responseValidator {@link ResponseValidator}
	 * @return {@link GetServicesViaId}
	 */
	public static GetServicesViaId getServicesViaIdInstance(IResponseValidator responseValidator) {
		if (getServicesViaId == null) {
			getServicesViaId = new GetServicesViaId(responseValidator);
		}
		return getServicesViaId;
	}

	/**
	 * Return the instance of {@link AddProducts}
	 * 
	 * @param responseValidator {@link ResponseValidator}
	 * @return {@link AddProducts}
	 */
	public static AddProducts getAddProductsInstance(IResponseValidator responseValidator) {
		if (addProducts == null) {
			addProducts = new AddProducts(responseValidator);
		}
		return addProducts;
	}
}
