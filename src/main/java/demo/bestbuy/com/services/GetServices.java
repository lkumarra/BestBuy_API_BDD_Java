package demo.bestbuy.com.services;

import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.helpers.apihelper.*;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.services.ServicesDBHelper;
import demo.bestbuy.com.modals.services.ServicesModal.GetServicesModal;

import static demo.bestbuy.com.helpers.apihelper.InstanceCreator.*;
import demo.bestbuy.com.baseapi.BaseAPI;


@Slf4j
public final class GetServices extends BaseAPI {

	public GetServices(IResponseValidator responseValidator) {
		super("/services", responseValidator);
	}

	public void executeGetServicesAPI() {
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(endPoint);
		log.warn("Response for endpoint : {} with method : {} is : {}", endPoint, "GET",
				responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	public void verifyResponseFromDB() {
		GetServicesModal actualResponse = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
				GetServicesModal.class);
		log.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		GetServicesModal expectedResponse = new GetServicesModal();
		expectedResponse.setData(ServicesDBHelper.getServicesFromDB());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		expectedResponse.setTotal(ServicesDBHelper.getTotalServices());
		log.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
