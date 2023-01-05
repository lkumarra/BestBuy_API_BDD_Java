package demo.bestbuy.com.services;


import demo.bestbuy.com.apihelper.AssertHelper;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.services.ServicesDBHelper;
import demo.bestbuy.com.services.ServicesModal.Datum;

public class GetServicesViaId extends BaseAPI {

	private int _id;

	public GetServicesViaId(IResponseValidator responseValidator) {
		super("/services/%s", responseValidator);
	}

	protected void executeGetServicesViaIdAPI(int id) {
		_id = id;
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace().performGetRequest(String.format(endPoint, id));
		InstanceCreator.getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	protected void getServicesViaIdResponseFromDB() {
		Datum actualResponse = InstanceCreator.getRestAssuredHelperInstace()
				.getMappedResponse(responseWrapper.getResponse(), Datum.class);
		Datum expectedResponse = ServicesDBHelper.getServicesFromDB(_id);
		if(!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
