package demo.bestbuy.com.categories;


import demo.bestbuy.com.baseapi.BaseAPI;
import static demo.bestbuy.com.helpers.apihelper.InstanceCreator.*;
import demo.bestbuy.com.helpers.responsevalidator.ResponseValidator;
import demo.bestbuy.com.helpers.scenariocontext.ScenarioContext;
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

	private GetCategories getCategories;

	public GetCategoriesSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> context) {
		getCategories = getCategoriesInstance(responseValidator);
		context.setContext(BaseAPI.class, getCategories);
	}

	@When("I get all categories")
	public void i_get_all_categories() {
		getCategories.executeGetCategoriesAPI();
	}

	@Then("Verify the categories list from Db")
	public void verify_the_categories_list_from_Db() {
		getCategories.verifyCategoriesFromDB();
	}
}
