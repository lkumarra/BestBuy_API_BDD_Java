package demo.bestbuy.com.commonsteps;

import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.scenariocontext.ScenarioContext;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import io.cucumber.java.en.Then;

/**
 * This class contains the Response Validation steps
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 Jul 2021
 */
public class VerifyResponseSteps {

	BaseAPI baseAPi;

	public VerifyResponseSteps(ScenarioContext context) {
		baseAPi = (BaseAPI) context.getContext(BaseAPI.class);
	}

	@Then("Response should be returned with status code {int}")
	public void products_list_returned_with_status_code(int statusCode) {
		ResponseModalWrapper responseModalWrapper = InstanceCreator.getResponseModalWrapperInstance();
		responseModalWrapper.setCode(statusCode);
		baseAPi.VerifyResponse(responseModalWrapper);
	}

	@Then("Response should not be returned with status code {int}, name as {string} and message as {string}")
	public void product_should_be_returned_with_status_code_name_as_and_message_as(int statusCode, String name,
			String message) {
		ResponseModalWrapper responseModalWrapper = InstanceCreator.getResponseModalWrapperInstance();
		responseModalWrapper.setCode(statusCode);
		responseModalWrapper.setName(name);
		responseModalWrapper.setMessage(message);
		baseAPi.VerifyResponse(responseModalWrapper);
	}

}
