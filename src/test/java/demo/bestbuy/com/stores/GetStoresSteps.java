package demo.bestbuy.com.stores;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.responsevalidator.ResponseValidator;
import demo.bestbuy.com.helpers.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class GetStoresSteps {

	private GetStores getStores;
	public GetStoresSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> scenarioContext) {
		getStores = new GetStores(responseValidator);
		scenarioContext.setContext(BaseAPI.class, getStores);
	}
	@When("I get all stores")
	public void i_get_all_stores() {
		getStores.executeGetStoresAPI();
	}

	
	@Then("Verify the stores list from Db")
	public void verify_the_stores_list_from_Db() {
		getStores.verifyStoresResponseFromDb();
	}

}
