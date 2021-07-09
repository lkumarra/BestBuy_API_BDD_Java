package demo.bestbuy.com.products;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;

/**
 * This class contains all the function related to GET /products/{id} API
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class GetProductViaId extends BaseAPI {

	private int id;

	public GetProductViaId(IResponseValidator responseValidator) {
		super("/products/%s", responseValidator);
	}

	/**
	 * Execute Get /products/{id} API.
	 * 
	 * @param id : Id to get product
	 */
	protected void extecuteGetProductViaIdAPI(int id) {
		this.id = id;
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace().performGetRequest(String.format(endPoint, id));
		InstanceCreator.getResponseValidatorInstace().setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the product retured in response from DB.
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	protected void verifyProductListFromDb() {
		GetProductDatum actualResponse = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(responseWrapper.getResponse(), GetProductDatum.class);
		GetProductDatum expectedResponse = ProductDBHelper.getProductViaId(id);
		if (!actualResponse.equals(expectedResponse)) {
			Assert.fail("Actual response is not equal to expected response");
		}
	}
}
