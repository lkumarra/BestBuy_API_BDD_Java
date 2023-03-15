package demo.bestbuy.com.commonsteps;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.apihelper.InstanceCreator;
import demo.bestbuy.com.helpers.scenariocontext.ScenarioContext;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import demo.bestbuy.com.wrapper.StatusCodeWrapper;
import demo.bestbuy.com.wrapper.StatusCodeWrapper.StatusCodeEnum;
import io.cucumber.java.en.Then;

/**
 * This class contains the Response Validation steps
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 Jul 2021
 */
public class VerifyResponseSteps {

	private BaseAPI baseAPi;

	public VerifyResponseSteps(ScenarioContext<BaseAPI> context) {
		baseAPi = context.getContext(BaseAPI.class);
	}

	@Then("Response should be returned with status code {string}")
	public void products_list_returned_with_status_code(String statusCodeEnumString) throws Exception {
		ResponseModalWrapper responseModalWrapper = InstanceCreator.getResponseModalWrapperInstance();
		StatusCodeEnum statusCodeEnum = Enum.valueOf(StatusCodeEnum.class, statusCodeEnumString);
		int statusCode = StatusCodeWrapper.getStatusCodeViaEnum(statusCodeEnum);
		responseModalWrapper.setCode(statusCode);
		baseAPi.VerifyResponse(responseModalWrapper);
	}

	@Then("Response should not be returned with status code {string}, name as {string} and message as {string}")
	public void product_should_be_returned_with_status_code_name_as_and_message_as(String statusCodeEnumString,
			String name, String message) throws Exception {
		ResponseModalWrapper responseModalWrapper = InstanceCreator.getResponseModalWrapperInstance();
		StatusCodeEnum statusCodeEnum = Enum.valueOf(StatusCodeEnum.class, statusCodeEnumString);
		int statusCode = StatusCodeWrapper.getStatusCodeViaEnum(statusCodeEnum);
		responseModalWrapper.setCode(statusCode);
		responseModalWrapper.setName(name);
		responseModalWrapper.setMessage(message);
		baseAPi.VerifyResponse(responseModalWrapper);
	}

	@Then("Response should not be returned with name as {string},message as {string}, status code {string} and errors as {string}")
	public void response_should_not_be_returned_with_name_as_message_as_status_code_and_errors_as(String name,
			String message, String statusCodeEnumString, String errors) throws Exception {
		ResponseModalWrapper responseModalWrapper = InstanceCreator.getResponseModalWrapperInstance();
		StatusCodeEnum statusCodeEnum = Enum.valueOf(StatusCodeEnum.class, statusCodeEnumString);
		int statusCode = StatusCodeWrapper.getStatusCodeViaEnum(statusCodeEnum);
		responseModalWrapper.setCode(statusCode);
		responseModalWrapper.setName(name);
		responseModalWrapper.setMessage(message);
		responseModalWrapper.setErrors(errors);
		baseAPi.VerifyResponse(responseModalWrapper);
	}
}