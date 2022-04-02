package demo.bestbuy.com.categories;


import demo.bestbut.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * This class contains the steps of GetCategories.feature
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class GetCategoriesSteps {

	private GetCategories _getCategories;

	public GetCategoriesSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> context) {
		_getCategories = InstanceCreator.getCategoriesInstance(responseValidator);
		context.setContext(BaseAPI.class, _getCategories);
	}

	@When("I get all categories")
	public void i_get_all_categories() {
		_getCategories.executeGetCategoriesAPI();
	}

	@Then("Verify the categories list from Db")
	public void verify_the_categories_list_from_Db() {
		_getCategories.verifyCategoriesFromDB();
	}
}
