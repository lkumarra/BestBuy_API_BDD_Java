package demo.bestbuy.com.stores;

import static demo.bestbuy.com.helpers.apihelper.InstanceCreator.*;
import demo.bestbuy.com.helpers.apihelper.*;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.stores.StoresDBHelper;
import demo.bestbuy.com.modals.stores.StoresModal.StoresData;
import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.baseapi.BaseAPI;

@Slf4j
public final class GetStoresViaId extends BaseAPI {

	private int storeId;

	public GetStoresViaId(IResponseValidator responseValidator) {
		super("/stores/%s", responseValidator);
	}

	public final void executeGetStoresViaIdAPI(int storeId) {
		this.storeId = storeId;
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(String.format(endPoint, storeId));
		log.warn("Response for endpoint : {} with method : {} is : {}", String.format(endPoint, storeId), "GET",
				responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	public final void verifyGetStoresViaIdFromDb() {
		StoresData actualResponse = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
				StoresData.class);
		log.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		StoresData expectedResponse = StoresDBHelper.getStoresFromDb().stream().filter(x -> x.getId() == storeId)
				.findFirst().get();
		log.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(expectedResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}

}
