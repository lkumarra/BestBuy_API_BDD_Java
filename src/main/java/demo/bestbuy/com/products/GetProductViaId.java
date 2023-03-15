package demo.bestbuy.com.products;


import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.apihelper.AssertHelper;
import demo.bestbuy.com.helpers.apihelper.InstanceCreator;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.products.ProductDBHelper;
import demo.bestbuy.com.modals.products.ProductModal.GetProductDatum;


/**
 * This class contains all the function related to GET /products/{id} API
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
@Slf4j
public final class GetProductViaId extends BaseAPI {

	private int id;

	public GetProductViaId(IResponseValidator responseValidator) {
		super("/products/%s", responseValidator);
	}

	/**
	 * Execute Get /products/{id} API.
	 * 
	 * @param id : Id to get product
	 */
	protected void executeGetProductViaIdAPI(int id) {
		this.id = id;
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace().performGetRequest(String.format(endPoint, id));
		log.warn("Response for endpoint : {} with metod : {} is : {} ", String.format(endPoint, id), "GET",
				responseWrapper.getResponse());
		InstanceCreator.getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the product returned in response from DB.
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	protected void verifyProductListFromDb() {
		GetProductDatum actualResponse = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(responseWrapper.getResponse(), GetProductDatum.class);
		log.warn("Response from API is : {}",
				InstanceCreator.getRestAssuredHelperInstace().serializedObject(actualResponse));
		GetProductDatum expectedResponse = ProductDBHelper.getProductViaId(id);
		log.warn("Data from Db is : {}",
				InstanceCreator.getRestAssuredHelperInstace().serializedObject(expectedResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
