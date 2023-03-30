package demo.bestbuy.com.services;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.data.services.ServicesData;
import demo.bestbuy.com.data.services.ServicesData.ServicesDataEnum;
import demo.bestbuy.com.helpers.responsevalidator.ResponseValidator;
import demo.bestbuy.com.helpers.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class GetServicesViaIdSteps {

	private final GetServicesViaId _getServicesViaId;

	public GetServicesViaIdSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> scenarioContext) {
		_getServicesViaId = GetServicesViaId.newGetServiceViaId(responseValidator);
		scenarioContext.setContext(BaseAPI.class, _getServicesViaId);
	}

	@When("I get {string} service")
	public void i_get_service(String service) {
		ServicesDataEnum servicesDataEnum = Enum.valueOf(ServicesDataEnum.class, service);
		int serviceId = ServicesData.getServicesData(servicesDataEnum);
		_getServicesViaId.executeGetServicesViaIdAPI(serviceId);
	}

	@Then("Verify the service from Db")
	public void verify_the_service_from_Db() {
		_getServicesViaId.getServicesViaIdResponseFromDB();
	}
}
