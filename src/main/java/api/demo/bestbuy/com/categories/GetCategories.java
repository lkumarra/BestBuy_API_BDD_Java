package demo.bestbuy.com.categories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bestbuy.com.apihelper.AssertHelper;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.categories.CategoriesModal.GetCategoryModal;
import demo.bestbuy.com.interfaces.IResponseValidator;

/**
 * This class contains method related to GET /categories API
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class GetCategories extends BaseAPI {

	private final Logger logger = LoggerFactory.getLogger(GetCategories.class);

	public GetCategories(IResponseValidator responseValidator) {
		super("/categories", responseValidator);
	}

	/**
	 * Execute Get /categories API.
	 */
	protected void executeGetCategoriesAPI() {
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace().performGetRequest(endPoint);
		logger.warn("Response for end point : {} with method : {} is : {}", endPoint, "GET",
				responseWrapper.getResponse());
		InstanceCreator.getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the categrories list returned in response from DB.
	 * 
	 * 
	 * 
	 */
	protected void verifyCategoriesFromDB() {
		GetCategoryModal actualResponse = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(responseWrapper.getResponse(), GetCategoryModal.class);
		logger.warn("Response from API is : {}",
				InstanceCreator.getRestAssuredHelperInstace().serializedObject(actualResponse));
		GetCategoryModal expectedResponse = new GetCategoryModal();
		expectedResponse.setData(CategoriesDBHelper.getCategoriesList());
		expectedResponse.setTotal(CategoriesDBHelper.getTotalCategoryCount());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		logger.warn("Data from Db is : {}",
				InstanceCreator.getRestAssuredHelperInstace().serializedObject(expectedResponse));
		if (!expectedResponse.equals(actualResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
