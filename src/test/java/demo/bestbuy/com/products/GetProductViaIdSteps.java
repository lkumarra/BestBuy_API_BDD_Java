package demo.bestbuy.com.products;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.data.products.ProductData;
import demo.bestbuy.com.data.products.ProductData.ProductDataEnum;
import demo.bestbuy.com.helpers.apihelper.InstanceCreator;
import demo.bestbuy.com.helpers.responsevalidator.ResponseValidator;
import demo.bestbuy.com.helpers.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * This class contains the steps related to GetProductViaId.feature
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class GetProductViaIdSteps {

	
	private GetProductViaId getProductViaId;

	public GetProductViaIdSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> context) {
		getProductViaId = InstanceCreator.getProductViaIdInstance(responseValidator);
		context.setContext(BaseAPI.class, getProductViaId);
	}

	@When("I get product with product {string}")
	@When("I try get product with product {string}")
	public void i_get_product_with_product(String productName) {
		ProductDataEnum dataEnum = Enum.valueOf(ProductDataEnum.class, productName);
		int productId = ProductData.getProductIdViaProductName(dataEnum);
		getProductViaId.extecuteGetProductViaIdAPI(productId);
	}

	@Then("Verify the product from Db")
	public void verify_the_product_from_Db() {
		getProductViaId.verifyProductListFromDb();
	}
}
