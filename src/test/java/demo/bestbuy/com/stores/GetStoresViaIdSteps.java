package demo.bestbuy.com.stores;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.data.stores.StoresData;
import demo.bestbuy.com.data.stores.StoresData.StoresDataEnum;
import demo.bestbuy.com.helpers.responsevalidator.ResponseValidator;
import demo.bestbuy.com.helpers.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class GetStoresViaIdSteps {
	
	private final GetStoresViaId getStoreViaId;
	
	public GetStoresViaIdSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> scenarioContext) {
		getStoreViaId = GetStoresViaId.newGetStoresViaId(responseValidator);
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
