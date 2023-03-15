package demo.bestbuy.com.categories;

import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.data.categories.CategoriesData;
import demo.bestbuy.com.data.categories.CategoriesData.CategoriesDataEnum;
import demo.bestbuy.com.helpers.apihelper.InstanceCreator;
import demo.bestbuy.com.helpers.responsevalidator.ResponseValidator;
import demo.bestbuy.com.helpers.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class GetCategoriesViaIdSteps {

	private GetCategoriesViaId getCategoriesViaId;

	public GetCategoriesViaIdSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> scenarioContext) {
		getCategoriesViaId = InstanceCreator.getCategoriesViaIdInstance(responseValidator);
		scenarioContext.setContext(BaseAPI.class, getCategoriesViaId);
	}

	@When("I get category with id a {string}")
	@When("I try get categories with product {string}")
	public void i_get_category_with_id_a(String categoryId) {
		CategoriesDataEnum categoriesDataEnum = Enum.valueOf(CategoriesDataEnum.class, categoryId);
		String categoriesId = CategoriesData.getEnumString(categoriesDataEnum);
		getCategoriesViaId.executeGetCategoriesViaIdAPI(categoriesId);
	}

	@Then("Verify the category from Db")
	public void verify_the_category_from_Db() {
		getCategoriesViaId.verifyGetCategoriesViaIdFromDB();
	}
}
