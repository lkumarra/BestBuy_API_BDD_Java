package demo.bestbuy.com.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import demo.bestbut.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.productData.ProductData;
import demo.bestbuy.com.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetProductViaIdSteps {
	
	GetProductViaId getProductViaId;
	
	public GetProductViaIdSteps(ResponseValidator responseValidator, ScenarioContext context) {
		getProductViaId = new GetProductViaId(responseValidator);
		context.setContext(BaseAPI.class, getProductViaId);
	}
	
	@When("I get product with product {string}")
	@When("I try get product with product {string}")
	public void i_get_product_with_product(String productName){
		getProductViaId.extecuteGetProductViaIdAPI(ProductData.getProductIdViaProductName(productName));
	}

	@Then("Verify the product from Db")
	public void verify_the_product_from_Db() throws JsonMappingException, JsonProcessingException {
		getProductViaId.verifyProductListFromDb();
	}
}
