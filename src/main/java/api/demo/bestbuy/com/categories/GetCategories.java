package demo.bestbuy.com.categories;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.bestbut.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.constants.BDDConstants;
import demo.bestbuy.com.interfaces.IResponseValidator;

public class GetCategories extends BaseAPI {

	public GetCategories(IResponseValidator responseValidator) {
		super("/categories", responseValidator);
	}

	/**
	 * Execute Get /categories API.
	 */
	public void executeGetCategoriesAPI() {
		responseWrapper = BDDConstants.getResassuredHelper().performGetRequest(endPoint);
		new ResponseValidator().setResponseWrapper(responseWrapper);
	}

	/**
	 * Verify the categrories list returned in response from DB.
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public void verifyCategoriesFromDB() throws JsonMappingException, JsonProcessingException {
		GetCategoryModal actualResponse = new ObjectMapper().readValue(responseWrapper.getResponse(),
				GetCategoryModal.class);
		GetCategoryModal expectedResponse = new GetCategoryModal();
		expectedResponse.setData(CategoriesDBHelper.getCategoriesList());
		expectedResponse.setTotal(CategoriesDBHelper.getTotalCategoryCount());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		if (!expectedResponse.equals(actualResponse)) {
			Assert.fail("Actual and excpected response are not same");
		}
	}
}
