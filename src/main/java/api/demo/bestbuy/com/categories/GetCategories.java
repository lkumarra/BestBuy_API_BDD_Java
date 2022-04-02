package demo.bestbuy.com.categories;



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

	
	public GetCategories(IResponseValidator responseValidator) {
		super("/categories", responseValidator);
	}

	/**
	 * Execute Get /categories API.
	 */
	protected void executeGetCategoriesAPI() {
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace().performGetRequest(endPoint);
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
		GetCategoryModal expectedResponse = new GetCategoryModal();
		expectedResponse.setData(CategoriesDBHelper.getCategoriesList());
		expectedResponse.setTotal(CategoriesDBHelper.getTotalCategoryCount());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		if (!expectedResponse.equals(actualResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
