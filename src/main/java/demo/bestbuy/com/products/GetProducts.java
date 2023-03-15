package demo.bestbuy.com.products;

import lombok.extern.slf4j.Slf4j;
import static demo.bestbuy.com.helpers.apihelper.InstanceCreator.*;
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


	public GetProducts(IResponseValidator responseValidator) {
		super("/products", responseValidator);
	}

	/**
	 * Execute Get /products API.
	 */
	protected void executeGetProductsAPI() {
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(endPoint);
		log.warn("Response for endpoint : {} with metod : {} is : {} ", endPoint, "GET",
				responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the product list returned in response from DB.
	 * 
	 */
	protected void verifyResponseReturnedFromDB() {
		GetProductModal actualResponse = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
				GetProductModal.class);
		log.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		GetProductModal expectedResponse = new GetProductModal();
		expectedResponse.setData(ProductDBHelper.getProductsList());
		expectedResponse.setTotal(ProductDBHelper.getTotalProducts());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		log.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(expectedResponse));
		if (!expectedResponse.equals(actualResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
