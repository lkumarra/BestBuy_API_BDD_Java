package demo.bestbuy.com.services;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.responsevalidator.ResponseValidator;
import demo.bestbuy.com.helpers.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class GetServicesSteps {
	
	private final GetServices getServices;
	
	public GetServicesSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> scenarioContext) {
		getServices = GetServices.newGetServices(responseValidator);
		scenarioContext.setContext(BaseAPI.class, getServices);
	}
	@When("I get all services")
	public void i_get_all_services() {
		getServices.executeGetServicesAPI();
	}
	
	@Then("Verify the services list from Db")
	public void verify_the_services_list_from_Db() {
		getServices.verifyResponseFromDB();
	}

}
