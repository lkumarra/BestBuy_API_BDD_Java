package demo.bestbuy.com.services;

import static demo.bestbuy.com.helpers.apihelper.InstanceCreator.*;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.apihelper.AssertHelper;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.services.ServicesDBHelper;
import demo.bestbuy.com.modals.services.ServicesModal.Datum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class GetServicesViaId extends BaseAPI {

	private int _id;

	public GetServicesViaId(IResponseValidator responseValidator) {
		super("/services/%s", responseValidator);
	}

	protected final void executeGetServicesViaIdAPI(int id) {
		_id = id;
		responseWrapper = getRestAssuredHelperInstace().performGetRequest(String.format(endPoint, id));
		log.warn("Response for endpoint : {} with method : {} is : {}", String.format(endPoint, id), "GET",
				responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
	}

	protected final void getServicesViaIdResponseFromDB() {
		Datum actualResponse = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
				Datum.class);
		log.warn("Response from API is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		Datum expectedResponse = ServicesDBHelper.getServicesFromDB(_id);
		log.warn("Data from Db is : {}", getRestAssuredHelperInstace().serializedObject(actualResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
