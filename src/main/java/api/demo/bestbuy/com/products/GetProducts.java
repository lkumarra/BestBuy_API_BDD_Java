package demo.bestbuy.com.products;


import demo.bestbuy.com.apihelper.AssertHelper;
import demo.bestbuy.com.apihelper.InstanceCreator;
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

	public GetProducts(IResponseValidator responseValidator) {
		super("/products", responseValidator);
	}

	/**
	 * Execute Get /products API.
	 */
	protected void executeGetProuctsAPI() {
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace().performGetRequest(endPoint);
		InstanceCreator.getResponseValidatorInstace().setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the product list returned in response from DB.
	 * 
	 */
	protected void verifyResponseReturedFromDB() {
		GetProductModal actualResponse = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(responseWrapper.getResponse(), GetProductModal.class);
		GetProductModal expectedResponse = new GetProductModal();
		expectedResponse.setData(ProductDBHelper.getProductsList());
		expectedResponse.setTotal(ProductDBHelper.getTotalProducts());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		if (!expectedResponse.equals(actualResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
