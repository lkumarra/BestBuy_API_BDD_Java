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

	@Then("Products list returned with status code {int}")
	@Then("Categories list returned with status code {int}")
	@Then("Product should be returned with status code {int}")
	public void products_list_returned_with_status_code(Integer statusCode) {
		ResponseModalWrapper responseModalWrapper = new ResponseModalWrapper();
		responseModalWrapper.setCode(statusCode);
		baseAPi.VerifyResponse(responseModalWrapper);
	}
	
	@Then("Product should not be returned with status code {int}, name as {string} and message as {string}")
	public void product_should_be_returned_with_status_code_name_as_and_message_as(Integer statusCode, String name, String message) {
		ResponseModalWrapper responseModalWrapper = new ResponseModalWrapper();
		responseModalWrapper.setCode(statusCode);
		responseModalWrapper.setName(name);
		responseModalWrapper.setMessage(message);
		baseAPi.VerifyResponse(responseModalWrapper);
	}

}
