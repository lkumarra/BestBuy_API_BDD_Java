package demo.bestbuy.com.categories;

import org.testng.Assert;

import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;

public class GetCategoriesViaId extends BaseAPI {

	private String categoryId;

	public GetCategoriesViaId(IResponseValidator responseValidator) {
		super("/categories/%s", responseValidator);
	}

	protected void executeGetCategoriesViaIdAPI(String categoryId) {
		this.categoryId = categoryId;
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace()
				.performGetRequest(String.format(endPoint, categoryId));
		InstanceCreator.getResponseValidatorInstace().setResponseWrapper(responseWrapper);
	}

	protected void verifyGetCatgoriesViaIdFromDB() {
		GetCategoryDatum actualResponse = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(responseWrapper.getResponse(), GetCategoryDatum.class);
		GetCategoryDatum expectedResponse = CategoriesDBHelper.getCategoriesViaId(categoryId);
		if (!actualResponse.equals(expectedResponse)) {
			Assert.fail("Actual and expected response are not equal");
		}
	}

}
