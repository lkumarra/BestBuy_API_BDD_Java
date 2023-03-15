package demo.bestbuy.com.products;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.helpers.apihelper.InstanceCreator;
import demo.bestbuy.com.helpers.responsevalidator.ResponseValidator;
import demo.bestbuy.com.helpers.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * This class contains all steps of GetProduct.feature
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class GetProductSteps {

	private GetProducts getProducts;

	public GetProductSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> context) {
		getProducts = InstanceCreator.getProdcutsInstance(responseValidator);
		context.setContext(BaseAPI.class, getProducts);
	}

	@When("I get all products")
	public void i_get_all_products() {
		getProducts.executeGetProuctsAPI();
	}

	@Then("Verify the products list from Db")
	public void verify_the_products_list_from_Db(){
		getProducts.verifyResponseReturedFromDB();
	}
}
