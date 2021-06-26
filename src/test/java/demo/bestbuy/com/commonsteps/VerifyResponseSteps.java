package demo.bestbuy.com.commonsteps;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.scenariocontext.ScenarioContext;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import io.cucumber.java.en.Then;

public class VerifyResponseSteps{
	
	BaseAPI baseAPi;
	
	public VerifyResponseSteps(ScenarioContext context) {
		baseAPi = (BaseAPI)context.getContext(BaseAPI.class);
	}

	@Then("Products list returned with status code {string}")
	@Then("Categories list returned with status code {string}")
	public void products_list_returned_with_status_code(String string) {
		ResponseModalWrapper responseModalWrapper = new ResponseModalWrapper();
		responseModalWrapper.setCode(Integer.parseInt(string));
		baseAPi.VerifyResponse(responseModalWrapper);
	}
}
