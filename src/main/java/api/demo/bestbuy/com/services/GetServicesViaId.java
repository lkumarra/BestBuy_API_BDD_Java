package demo.bestbuy.com.services;

import org.testng.Assert;

import demo.bestbut.com.services.ServicesDBHelper;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;

public class GetServicesViaId extends BaseAPI {

	private int _id;

	public GetServicesViaId(IResponseValidator responseValidator) {
		super("/services/%s", responseValidator);
	}

	protected void executeGetServicesViaIdAPI(int id) {
		_id = id;
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace().performGetRequest(String.format(endPoint, id));
		InstanceCreator.getResponseValidatorInstace().setResponseWrapper(responseWrapper);
	}

	protected void getServicesViaIdResponseFromDB() {
		Datum actualResponse = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(responseWrapper.getResponse(), Datum.class);
		Datum expectedResponse = ServicesDBHelper.getServicesFromDB(_id);
		if(!actualResponse.equals(expectedResponse)) {
			Assert.fail("Actual and expected response are not equal");
		}
	}
}
