package demo.bestbuy.com.services;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.apihelper.AssertHelper;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.restassuredhelper.RestAssuredHelper;
import demo.bestbuy.com.helpers.services.ServicesDBHelper;
import demo.bestbuy.com.modals.services.ServicesModal.Datum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class GetServicesViaId extends BaseAPI {

	private int _id;

	private GetServicesViaId(IResponseValidator responseValidator) {
		super("/services/%s", responseValidator);
	}

	public static GetServicesViaId newGetServiceViaId(IResponseValidator responseValidator){
		return new GetServicesViaId(responseValidator);
	}

	public void executeGetServicesViaIdAPI(int id) {
		_id = id;
		responseWrapper = RestAssuredHelper.newRestAssuredHelper().performGetRequest(String.format(endPoint, id));
		log.warn("Response for endpoint : {} with method : {} is : {}", String.format(endPoint, id), "GET",
				responseWrapper.getResponse());
		responseValidator.setResponseWrapper(responseWrapper);
	}

	public void getServicesViaIdResponseFromDB() {
		Datum actualResponse = RestAssuredHelper.newRestAssuredHelper().getMappedResponse(responseWrapper.getResponse(),
				Datum.class);
		log.warn("Response from API is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		Datum expectedResponse = ServicesDBHelper.getServicesFromDB(_id);
		log.warn("Data from Db is : {}", RestAssuredHelper.newRestAssuredHelper().serializedObject(actualResponse));
		if (!actualResponse.equals(expectedResponse)) {
			AssertHelper.AssertFail(actualResponse, expectedResponse);
		}
	}
}
