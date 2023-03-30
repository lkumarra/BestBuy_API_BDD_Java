package demo.bestbuy.com.products;

import demo.bestbuy.com.helpers.restassuredhelper.RestAssuredHelper;
import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.apihelper.AssertHelper;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.products.ProductDBHelper;
import demo.bestbuy.com.modals.products.ProductModal.GetProductModal;


/**
 * This class contains all the method related to GET /products API
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date
 */

@Slf4j
public final class GetProducts extends BaseAPI {


	private GetProducts(IResponseValidator responseValidator) {
		super("/products", responseValidator);
	}

	public static GetProducts newGetProducts(IResponseValidator responseValidator){
		return new GetProducts(responseValidator);
	}

	/**
	 * Execute Get /products API.
	 */
	public void executeGetProductsAPI() {
		responseWrapper = RestAssuredHelper.newRestAssuredHelper().performGetRequest(endPoint);
		log.warn("Response for endpoint : {} with method : {} is : {} ", endPoint, "GET",
				responseWrapper.getResponse());
		responseValidator.setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the product list returned in response from DB.
	 * 
	 */
	public void verifyResponseReturnedFromDB() {
		GetProductModal actualResponse = RestAssuredHelper.newRestAssuredHelper().getMappedResponse(responseWrapper.getResponse(),
				GetProductModal.class);
		log.warn("Response from API is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		GetProductModal expectedResponse = new GetProductModal();
		expectedResponse.setData(ProductDBHelper.getProductsList());
		expectedResponse.setTotal(ProductDBHelper.getTotalProducts());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		log.warn("Data from Db is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(expectedResponse));
		if (!expectedResponse.equals(actualResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
