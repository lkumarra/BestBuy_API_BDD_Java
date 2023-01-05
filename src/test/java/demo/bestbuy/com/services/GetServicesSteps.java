package demo.bestbuy.com.services;

import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetServicesSteps {
	
	private GetServices getSevices;
	
	public GetServicesSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> scenarioContext) {
		getSevices = InstanceCreator.getSevicesInstance(responseValidator);
		scenarioContext.setContext(BaseAPI.class, getSevices);
	}
	@When("I get all services")
	public void i_get_all_services() {
	    getSevices.executeGetServicesAPI();
	}
	
	@Then("Verify the services list from Db")
	public void verify_the_services_list_from_Db() {
	    getSevices.verifyResponseFromDB();
	}

}
