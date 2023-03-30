package demo.bestbuy.com.categories;

import demo.bestbuy.com.helpers.restassuredhelper.RestAssuredHelper;
import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.apihelper.AssertHelper;
import demo.bestbuy.com.helpers.categories.CategoriesDBHelper;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.modals.categories.CategoriesModal.GetCategoryDatum;

@Slf4j
public final class GetCategoriesViaId extends BaseAPI {

	private String categoryId;

	private GetCategoriesViaId(IResponseValidator responseValidator) {
		super("/categories/%s", responseValidator);
	}

	public static GetCategoriesViaId newGetCategoriesViaId(IResponseValidator responseValidator){
		return  new GetCategoriesViaId(responseValidator);
	}

	protected void executeGetCategoriesViaIdAPI(String categoryId) {
		this.categoryId = categoryId;
		responseWrapper = RestAssuredHelper.newRestAssuredHelper()
				.performGetRequest(String.format(endPoint, categoryId));
		log.warn("Response for end point : {} with method : {} is : {}", String.format(endPoint, categoryId), "GET",
				responseWrapper.getResponse());
		responseValidator.setResponseWrapper(responseWrapper);
	}

	protected void verifyGetCategoriesViaIdFromDB() {
		GetCategoryDatum actualResponse = RestAssuredHelper.newRestAssuredHelper()
				.getMappedResponse(responseWrapper.getResponse(), GetCategoryDatum.class);
		log.warn("Response from API is : {}",
				RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		GetCategoryDatum expectedResponse = CategoriesDBHelper.getCategoriesViaId(categoryId);
		log.warn("Data from Db is : {}",
				RestAssuredHelper.newRestAssuredHelper().serializedObject(expectedResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}

}
