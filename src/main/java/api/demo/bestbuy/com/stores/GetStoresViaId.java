package demo.bestbuy.com.stores;

import static demo.bestbuy.com.apihelper.InstanceCreator.getResponseValidatorInstace;
import static demo.bestbuy.com.apihelper.InstanceCreator.getRestAssuredHelperInstace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bestbuy.com.apihelper.AssertHelper;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.stores.StoresModal.StoresData;

public class GetStoresViaId extends BaseAPI{
	
	private int storeId;
	
	private Logger logger = LoggerFactory.getLogger(GetStoresViaId.class);
	
	public GetStoresViaId(IResponseValidator responseValidator) {
		super("/stores/%s", responseValidator);
	}
	
	
	public void executeGetStoresViaIdAPI(int storeId) {
		this.storeId = storeId;
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(String.format(endPoint, storeId));
		logger.warn("Response for endpoint : {} with method : {} is : {}", String.format(endPoint, storeId), "GET",
				responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}
	
	
	public void verifyGetStoresViaIdFromDb() {
		StoresData actualResponse =  getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(), StoresData.class);
		logger.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		StoresData expectedResponse = StoresDBHelper.getStoresFromDb().stream().filter(x->x.getId() == storeId).findFirst().get();
		logger.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(expectedResponse));
		if(!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}

}
