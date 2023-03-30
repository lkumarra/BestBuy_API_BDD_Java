package demo.bestbuy.com.services;

import demo.bestbuy.com.helpers.restassuredhelper.RestAssuredHelper;
import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.helpers.apihelper.*;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.services.ServicesDBHelper;
import demo.bestbuy.com.modals.services.ServicesModal.GetServicesModal;
import demo.bestbuy.com.baseapi.BaseAPI;


@Slf4j
public final class GetServices extends BaseAPI {

	private GetServices(IResponseValidator responseValidator) {
		super("/services", responseValidator);
	}

	public static GetServices newGetServices(IResponseValidator responseValidator){
		return new GetServices(responseValidator);
	}

	public void executeGetServicesAPI() {
		responseWrapper = RestAssuredHelper.newRestAssuredHelper().performGetRequest(endPoint);
		log.warn("Response for endpoint : {} with method : {} is : {}", endPoint, "GET",
				responseWrapper.getResponse());
		responseValidator.setResponseWrapper(responseWrapper);
	}

	public void verifyResponseFromDB() {
		GetServicesModal actualResponse = RestAssuredHelper.newRestAssuredHelper().getMappedResponse(responseWrapper.getResponse(),
				GetServicesModal.class);
		log.warn("Response from API is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		GetServicesModal expectedResponse = new GetServicesModal();
		expectedResponse.setData(ServicesDBHelper.getServicesFromDB());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		expectedResponse.setTotal(ServicesDBHelper.getTotalServices());
		log.warn("Data from Db is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
