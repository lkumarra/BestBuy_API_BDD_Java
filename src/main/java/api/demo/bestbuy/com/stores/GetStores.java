package demo.bestbuy.com.stores;

import static demo.bestbuy.com.apihelper.InstanceCreator.getResponseValidatorInstace;
import static demo.bestbuy.com.apihelper.InstanceCreator.getRestAssuredHelperInstace;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bestbuy.com.apihelper.AssertHelper;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.stores.StoresModal.GetStoresModal;
import demo.bestbuy.com.stores.StoresModal.StoresData;

public class GetStores extends BaseAPI {

	private Logger logger = LoggerFactory.getLogger(GetStores.class);

	public GetStores(IResponseValidator responseValidator) {
		super("/stores", responseValidator);
	}

	/**
	 * Execute GET /stores API
	 */
	public void executeGetStoresAPI() {
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(endPoint);
		logger.warn("Response for endpoint : {} with method : {} is : {}", endPoint, "GET",
				responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	/**
	 * Verfiy stores response from DB.
	 */
	public void verifyStoresResponseFromDb() {
		GetStoresModal actualResponse = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
				GetStoresModal.class);
		logger.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		List<StoresData> storesList = StoresDBHelper.getStoresFromDb();
		GetStoresModal expectedResponse = new GetStoresModal();
		expectedResponse.setData(storesList);
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		expectedResponse.setTotal(StoresDBHelper.getTotalStores());
		logger.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		if (!expectedResponse.equals(actualResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}

	}

}
