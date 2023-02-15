package demo.bestbuy.com.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bestbuy.com.apihelper.AssertHelper;
import static demo.bestbuy.com.apihelper.InstanceCreator.*;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.products.ProductModal.GetProductModal;

/**
 * This class contains all the method related to GET /products API
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date
 */
public class GetProducts extends BaseAPI {

	private final Logger logger = LoggerFactory.getLogger(GetProducts.class);

	public GetProducts(IResponseValidator responseValidator) {
		super("/products", responseValidator);
	}

	/**
	 * Execute Get /products API.
	 */
	protected void executeGetProuctsAPI() {
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(endPoint);
		logger.warn("Response for endpoint : {} with metod : {} is : {} ", endPoint, "GET",
				responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the product list returned in response from DB.
	 * 
	 */
	protected void verifyResponseReturedFromDB() {
		GetProductModal actualResponse = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
				GetProductModal.class);
		logger.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		GetProductModal expectedResponse = new GetProductModal();
		expectedResponse.setData(ProductDBHelper.getProductsList());
		expectedResponse.setTotal(ProductDBHelper.getTotalProducts());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		logger.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(expectedResponse));
		if (!expectedResponse.equals(actualResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
