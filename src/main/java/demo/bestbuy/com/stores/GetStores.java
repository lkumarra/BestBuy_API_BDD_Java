package demo.bestbuy.com.stores;

import java.util.*;
import demo.bestbuy.com.helpers.apihelper.*;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.restassuredhelper.RestAssuredHelper;
import demo.bestbuy.com.helpers.stores.StoresDBHelper;
import demo.bestbuy.com.modals.stores.StoresModal.GetStoresModal;
import demo.bestbuy.com.modals.stores.StoresModal.StoresData;
import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.baseapi.BaseAPI;

@Slf4j
public final class GetStores extends BaseAPI {

	private GetStores(IResponseValidator responseValidator) {
		super("/stores", responseValidator);
	}

	public static GetStores newGetStores(IResponseValidator responseValidator){
		return new GetStores(responseValidator);
	}

	/**
	 * Execute GET /stores API
	 */
	public void executeGetStoresAPI() {
		responseWrapper = RestAssuredHelper.newRestAssuredHelper().performGetRequest(endPoint);
		log.warn("Response for endpoint : {} with method : {} is : {}", endPoint, "GET", responseWrapper.getResponse());
		responseValidator.setResponseWrapper(responseWrapper);
	}

	/**
	 * Verfiy stores response from DB.
	 */
	public void verifyStoresResponseFromDb() {
		GetStoresModal actualResponse = RestAssuredHelper.newRestAssuredHelper().getMappedResponse(responseWrapper.getResponse(),
				GetStoresModal.class);
		log.warn("Response from API is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		List<StoresData> storesList = StoresDBHelper.getStoresFromDb();
		GetStoresModal expectedResponse = new GetStoresModal();
		expectedResponse.setData(storesList);
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		expectedResponse.setTotal(StoresDBHelper.getTotalStores());
		log.warn("Data from Db is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		if (!expectedResponse.equals(actualResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}

	}

}
