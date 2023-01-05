package demo.bestbuy.com.products;

import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.apihelper.StringHelper;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddProductSteps {
	
	private AddProducts _aAddProducts;
	
	public AddProductSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> scenarioContext) {
		_aAddProducts = InstanceCreator.getAddProductsInstance(responseValidator);
		scenarioContext.setContext(BaseAPI.class, _aAddProducts);
	}

	@When("I try create a product with name as {string}, type as {string}, price as {int}, shipping as {int}, upc as {string},description as {string},manufacturer as {string}, model as {string}, url as {string} image as {string}")
	@When("I create a product with name as {string}, type as {string}, price as {int}, shipping as {int}, upc as {string},description as {string},manufacturer as {string}, model as {string},url as {string} image as {string}")
	public void i_create_a_product_with_name_as_type_as_price_as_shipping_as_upc_as_description_as_manufacturer_as_model_as_url_as_image_as(
			String name, String type, int price, int shipping, String upc, String description, String manufacturer,
			String model, String url, String image) {
		name = StringHelper.getRequestedParsedString(name);
		type = StringHelper.getRequestedParsedString(type);
		upc = StringHelper.getRequestedParsedString(upc);
		description = StringHelper.getRequestedParsedString(description);
		manufacturer = StringHelper.getRequestedParsedString(manufacturer);
		model = StringHelper.getRequestedParsedString(model);
		url = StringHelper.getRequestedParsedString(url);
		image = StringHelper.getRequestedParsedString(image);
		_aAddProducts.executePostProductAPI(name, type, price, upc, shipping, description, manufacturer, model, url, image);
	}

	@Then("Verify product created in Db.")
	public void verify_product_created_in_Db() {
		_aAddProducts.verifyAddedProductFromDB();
	}

}
