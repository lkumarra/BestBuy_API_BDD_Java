package demo.bestbuy.com.products;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.bestbut.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.constants.BDDConstants;
import demo.bestbuy.com.interfaces.IResponseValidator;

public class GetProductViaId extends BaseAPI {

	private int id;

	public GetProductViaId(IResponseValidator responseValidator) {
		super("/products", responseValidator);
	}

	/**
	 * Execute Get /products/{id} API.
	 * 
	 * @param id : Id to get product
	 */
	public void extecuteGetProductViaIdAPI(int id) {
		this.id = id;
		responseWrapper = BDDConstants.getResassuredHelper().performGetRequest(endPoint + "/" + id);
		new ResponseValidator().setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the product retured in response from DB.
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public void verifyProductListFromDb() throws JsonMappingException, JsonProcessingException {
		GetProductDatum actualResponse = new ObjectMapper().readValue(responseWrapper.getResponse(),
				GetProductDatum.class);
		GetProductDatum expectedResponse = ProductDBHelper.getProductViaId(id);
		if (!actualResponse.equals(expectedResponse)) {
			Assert.fail("Actual response is not equal to expected response");
		}
	}
}
