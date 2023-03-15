package demo.bestbuy.com.stores;

import static demo.bestbuy.com.helpers.apihelper.InstanceCreator.*;
import java.util.*;
import demo.bestbuy.com.helpers.apihelper.*;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.stores.StoresDBHelper;
import demo.bestbuy.com.modals.stores.StoresModal.GetStoresModal;
import demo.bestbuy.com.modals.stores.StoresModal.StoresData;
import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.baseapi.BaseAPI;

@Slf4j
public final class GetStores extends BaseAPI {

	public GetStores(IResponseValidator responseValidator) {
		super("/stores", responseValidator);
	}

	/**
	 * Execute GET /stores API
	 */
	public final void executeGetStoresAPI() {
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(endPoint);
		log.warn("Response for endpoint : {} with method : {} is : {}", endPoint, "GET", responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	/**
	 * Verfiy stores response from DB.
	 */
	public final void verifyStoresResponseFromDb() {
		GetStoresModal actualResponse = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
				GetStoresModal.class);
		log.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		List<StoresData> storesList = StoresDBHelper.getStoresFromDb();
		GetStoresModal expectedResponse = new GetStoresModal();
		expectedResponse.setData(storesList);
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		expectedResponse.setTotal(StoresDBHelper.getTotalStores());
		log.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		if (!expectedResponse.equals(actualResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}

	}

}
