package demo.bestbuy.com.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bestbuy.com.apihelper.AssertHelper;
import static demo.bestbuy.com.apihelper.InstanceCreator.*;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.services.ServicesDBHelper;
import demo.bestbuy.com.services.ServicesModal.GetServicesModal;

public class GetServices extends BaseAPI {

	private Logger logger = LoggerFactory.getLogger(GetServices.class);

	public GetServices(IResponseValidator responseValidator) {
		super("/services", responseValidator);
	}

	public void executeGetServicesAPI() {
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(endPoint);
		logger.warn("Response for endpoint : {} with method : {} is : {}", endPoint, "GET",
				responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	public void verifyResponseFromDB() {
		GetServicesModal actualResponse = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
				GetServicesModal.class);
		logger.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		GetServicesModal expectedResponse = new GetServicesModal();
		expectedResponse.setData(ServicesDBHelper.getServicesFromDB());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		expectedResponse.setTotal(ServicesDBHelper.getTotalServices());
		logger.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
