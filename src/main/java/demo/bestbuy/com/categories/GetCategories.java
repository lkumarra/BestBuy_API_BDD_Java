package demo.bestbuy.com.categories;

import demo.bestbuy.com.helpers.restassuredhelper.RestAssuredHelper;
import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.apihelper.AssertHelper;
import demo.bestbuy.com.helpers.categories.CategoriesDBHelper;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.modals.categories.CategoriesModal.GetCategoryModal;

/**
 * This class contains method related to GET /categories API
 *
 * @author Lavendra Kumar Rajput
 * @Date 2 July 2021
 */
@Slf4j
public final class GetCategories extends BaseAPI {

    private GetCategories(IResponseValidator responseValidator) {
        super("/categories", responseValidator);
    }

    public static GetCategories newGetCategories(IResponseValidator responseValidator){
        return new GetCategories(responseValidator);
    }

    /**
     * Execute Get /categories API.
     */
    protected void executeGetCategoriesAPI() {
        responseWrapper = RestAssuredHelper.newRestAssuredHelper().performGetRequest(endPoint);
        log.warn("Response for end point : {} with method : {} is : {}", endPoint, "GET",
                responseWrapper.getResponse());
        responseValidator.setResponseWrapper(responseWrapper);
    }

    /**
     * Verify the categrories list returned in response from DB.
     */
    protected void verifyCategoriesFromDB() {
        GetCategoryModal actualResponse = RestAssuredHelper.newRestAssuredHelper()
                .getMappedResponse(responseWrapper.getResponse(), GetCategoryModal.class);
        log.warn("Response from API is : {}",
                RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
        GetCategoryModal expectedResponse = new GetCategoryModal();
        expectedResponse.setData(CategoriesDBHelper.getCategoriesList());
        expectedResponse.setTotal(CategoriesDBHelper.getTotalCategoryCount());
        expectedResponse.setLimit(10);
        expectedResponse.setSkip(0);
        log.warn("Data from Db is : {}",
                RestAssuredHelper.newRestAssuredHelper().serializedObject(expectedResponse));
        if (!expectedResponse.equals(actualResponse)) {
            AssertHelper.AssertFail(actualResponse, expectedResponse);
        }
    }
}
