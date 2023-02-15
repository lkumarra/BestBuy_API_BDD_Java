package demo.bestbuy.com.categories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bestbuy.com.apihelper.AssertHelper;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.categories.CategoriesModal.GetCategoryDatum;
import demo.bestbuy.com.interfaces.IResponseValidator;

public class GetCategoriesViaId extends BaseAPI {

	private final Logger logger = LoggerFactory.getLogger(GetCategories.class);

	private String categoryId;

	public GetCategoriesViaId(IResponseValidator responseValidator) {
		super("/categories/%s", responseValidator);
	}

	protected void executeGetCategoriesViaIdAPI(String categoryId) {
		this.categoryId = categoryId;
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace()
				.performGetRequest(String.format(endPoint, categoryId));
		logger.warn("Response for end point : {} with method : {} is : {}", String.format(endPoint, categoryId), "GET",
				responseWrapper.getResponse());
		InstanceCreator.getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	protected void verifyGetCatgoriesViaIdFromDB() {
		GetCategoryDatum actualResponse = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(responseWrapper.getResponse(), GetCategoryDatum.class);
		logger.warn("Response from API is : {}",
				InstanceCreator.getRestAssuredHelperInstace().serializedObject(actualResponse));
		GetCategoryDatum expectedResponse = CategoriesDBHelper.getCategoriesViaId(categoryId);
		logger.warn("Data from Db is : {}",
				InstanceCreator.getRestAssuredHelperInstace().serializedObject(expectedResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}

}
