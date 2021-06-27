package demo.bestbuy.com.products;

import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.bestbut.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.constants.BDDConstants;
import demo.bestbuy.com.interfaces.IResponseValidator;

public class GetProducts extends BaseAPI {

	public GetProducts(IResponseValidator responseValidator) {
		super("/products", responseValidator);
	}

	/**
	 * Execute Get /products API.
	 */
	public void executeGetProuctsAPI() {
		responseWrapper = BDDConstants.getResassuredHelper().performGetRequest(endPoint);
		new ResponseValidator().setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the product list returned in response from DB.
	 * 
	 * @throws Exception
	 */
	public void verifyResponseReturedFromDB() throws Exception {
		GetProductModal actualResponse = new ObjectMapper().readValue(responseWrapper.getResponse(),
				GetProductModal.class);
		GetProductModal expectedResponse = new GetProductModal();
		expectedResponse.setData(ProductDBHelper.getProductsList());
		expectedResponse.setTotal(ProductDBHelper.getTotalProducts());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		if (!expectedResponse.equals(actualResponse)) {
			Assert.fail("Actual and expeted response are not equal");
		}
	}
}
