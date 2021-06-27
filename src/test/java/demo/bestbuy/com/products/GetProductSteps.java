package demo.bestbuy.com.products;


import demo.bestbut.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetProductSteps {
	
	GetProducts getProducts;
	
	public GetProductSteps(ResponseValidator responseValidator, ScenarioContext context) {
		getProducts = new GetProducts(responseValidator);
		context.setContext(BaseAPI.class, getProducts);
	}
	
    @When("I get all products")
    public void i_get_all_products() {
       getProducts.executeGetProuctsAPI();
    }

    @Then("Verify the products list from Db")
    public void verify_the_products_list_from_Db() throws Exception {
       getProducts.verifyResponseReturedFromDB();
    }
}
