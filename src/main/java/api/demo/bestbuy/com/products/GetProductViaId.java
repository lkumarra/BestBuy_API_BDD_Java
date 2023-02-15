package demo.bestbuy.com.products;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import demo.bestbuy.com.apihelper.AssertHelper;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.products.ProductModal.GetProductDatum;

/**
 * This class contains all the function related to GET /products/{id} API
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class GetProductViaId extends BaseAPI {

	private final Logger logger = LoggerFactory.getLogger(GetProductViaId.class);
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
		logger.warn("Response for endpoint : {} with metod : {} is : {} ", String.format(endPoint, id), "GET",
				responseWrapper.getResponse());
		InstanceCreator.getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
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
		logger.warn("Response from API is : {}",
				InstanceCreator.getRestAssuredHelperInstace().serializedObject(actualResponse));
		GetProductDatum expectedResponse = ProductDBHelper.getProductViaId(id);
		logger.warn("Data from Db is : {}",
				InstanceCreator.getRestAssuredHelperInstace().serializedObject(expectedResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
