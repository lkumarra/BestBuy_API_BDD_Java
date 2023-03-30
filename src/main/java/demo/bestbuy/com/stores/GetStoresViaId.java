package demo.bestbuy.com.stores;
import demo.bestbuy.com.helpers.apihelper.*;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.restassuredhelper.RestAssuredHelper;
import demo.bestbuy.com.helpers.stores.StoresDBHelper;
import demo.bestbuy.com.modals.stores.StoresModal.StoresData;
import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.baseapi.BaseAPI;

@Slf4j
public final class GetStoresViaId extends BaseAPI {

	private int storeId;

	private GetStoresViaId(IResponseValidator responseValidator) {
		super("/stores/%s", responseValidator);
	}

	public static GetStoresViaId newGetStoresViaId(IResponseValidator responseValidator){
		return new GetStoresViaId(responseValidator);
	}

	public void executeGetStoresViaIdAPI(int storeId) {
		this.storeId = storeId;
		responseWrapper = RestAssuredHelper.newRestAssuredHelper().performGetRequest(String.format(endPoint, storeId));
		log.warn("Response for endpoint : {} with method : {} is : {}", String.format(endPoint, storeId), "GET",
				responseWrapper.getResponse());
		responseValidator.setResponseWrapper(responseWrapper);
	}

	public void verifyGetStoresViaIdFromDb() {
		StoresData actualResponse = RestAssuredHelper.newRestAssuredHelper().getMappedResponse(responseWrapper.getResponse(),
				StoresData.class);
		log.warn("Response from API is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		StoresData expectedResponse = StoresDBHelper.getStoresFromDb().stream().filter(x -> x.getId() == storeId)
				.findFirst().get();
		log.warn("Data from Db is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(expectedResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}

}
