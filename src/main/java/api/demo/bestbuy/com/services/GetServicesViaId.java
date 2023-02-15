package demo.bestbuy.com.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bestbuy.com.apihelper.AssertHelper;
import static demo.bestbuy.com.apihelper.InstanceCreator.*;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.services.ServicesDBHelper;
import demo.bestbuy.com.services.ServicesModal.Datum;

public class GetServicesViaId extends BaseAPI {

	private Logger logger = LoggerFactory.getLogger(GetServicesViaId.class);

	private int _id;

	public GetServicesViaId(IResponseValidator responseValidator) {
		super("/services/%s", responseValidator);
	}

	protected void executeGetServicesViaIdAPI(int id) {
		_id = id;
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(String.format(endPoint, id));
		logger.warn("Response for endpoint : {} with method : {} is : {}", String.format(endPoint, id), "GET",
				responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	protected void getServicesViaIdResponseFromDB() {
		Datum actualResponse = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
				Datum.class);
		logger.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		Datum expectedResponse = ServicesDBHelper.getServicesFromDB(_id);
		logger.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
