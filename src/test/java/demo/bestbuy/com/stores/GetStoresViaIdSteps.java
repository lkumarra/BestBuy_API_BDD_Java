package demo.bestbuy.com.stores;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.scenariocontext.ScenarioContext;
import demo.bestbuy.com.storesData.StoresData;
import demo.bestbuy.com.storesData.StoresData.StoresDataEnum;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetStoresViaIdSteps {
	
	private GetStoresViaId getStoreViaId;
	
	public GetStoresViaIdSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> scenarioContext) {
		getStoreViaId = new GetStoresViaId(responseValidator);
		scenarioContext.setContext(BaseAPI.class, getStoreViaId);
	}

	@When("I get {string} stores")
	@When("I try to get {string} stores")
	public void i_get_stores(String stores) {
		StoresDataEnum  storesDataEnum = Enum.valueOf(StoresDataEnum.class, stores);
		int storeId = StoresData.getStoresData(storesDataEnum);
		getStoreViaId.executeGetStoresViaIdAPI(storeId);
	}

	@Then("Verify stores data from Db.")
	public void verify_stores_data_from_Db() {
		getStoreViaId.verifyGetStoresViaIdFromDb();
	}

}
