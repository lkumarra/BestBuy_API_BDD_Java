package demo.bestbuy.com.products;


import demo.bestbuy.com.helpers.restassuredhelper.RestAssuredHelper;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.apihelper.AssertHelper;
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

	private GetProductViaId(IResponseValidator responseValidator) {
		super("/products/%s", responseValidator);
	}

	public static GetProductViaId newGetProductsViaId(IResponseValidator responseValidator){
		return new GetProductViaId(responseValidator);
	}

	/**
	 * Execute Get /products/{id} API.
	 * 
	 * @param id : Id to get product
	 */
	public void executeGetProductViaIdAPI(int id) {
		this.id = id;
		responseWrapper = RestAssuredHelper.newRestAssuredHelper().performGetRequest(String.format(endPoint, id));
		log.warn("Response for endpoint : {} with metod : {} is : {} ", String.format(endPoint, id), "GET",
				responseWrapper.getResponse());
		responseValidator.setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the product returned in response from DB.
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public void verifyProductListFromDb() {
		GetProductDatum actualResponse = RestAssuredHelper.newRestAssuredHelper()
				.getMappedResponse(responseWrapper.getResponse(), GetProductDatum.class);
		log.warn("Response from API is : {}",
				RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		GetProductDatum expectedResponse = ProductDBHelper.getProductViaId(id);
		log.warn("Data from Db is : {}",
				RestAssuredHelper.newRestAssuredHelper().serializedObject(expectedResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
