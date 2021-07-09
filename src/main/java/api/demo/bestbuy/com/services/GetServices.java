package demo.bestbuy.com.services;

import org.testng.Assert;

import demo.bestbut.com.services.ServicesDBHelper;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;

public class GetServices extends BaseAPI {

	public GetServices(IResponseValidator responseValidator) {
		super("/services", responseValidator);
	}

	public void executeGetServicesAPI() {
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace().performGetRequest(endPoint);
		InstanceCreator.getResponseValidatorInstace().setResponseWrapper(responseWrapper);
	}

	public void verifyResponseFromDB() {
		GetServicesModal actualResponse = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(responseWrapper.getResponse(), GetServicesModal.class);
		GetServicesModal expectedResponse = new GetServicesModal();
		expectedResponse.setData(ServicesDBHelper.getServicesFromDB());
		expectedResponse.setLimit(10);
		expectedResponse.setSkip(0);
		expectedResponse.setTotal(ServicesDBHelper.getTotalServices());
		if(!actualResponse.equals(expectedResponse)) {
			Assert.fail("Actual and expected response are not equal");
		}
	}
}
