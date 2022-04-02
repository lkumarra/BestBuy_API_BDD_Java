package demo.bestbuy.com.categories;

import demo.bestbut.com.responsevalidator.ResponseValidator;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.categoriesData.CategoriesData;
import demo.bestbuy.com.categoriesData.CategoriesData.CategoriesDataEnum;
import demo.bestbuy.com.scenariocontext.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetCategoriesViaIdSteps {

	private GetCategoriesViaId _getCategoriesViaId;

	public GetCategoriesViaIdSteps(ResponseValidator responseValidator, ScenarioContext<BaseAPI> scenarioContext) {
		_getCategoriesViaId = InstanceCreator.getCategoriesViaIdInstance(responseValidator);
		scenarioContext.setContext(BaseAPI.class, _getCategoriesViaId);
	}

	@When("I get category with id a {string}")
	@When("I try get categories with product {string}")
	public void i_get_category_with_id_a(String categoryId) {
		CategoriesDataEnum categoriesDataEnum = Enum.valueOf(CategoriesDataEnum.class, categoryId);
		String categoriesId = CategoriesData.getEnumString(categoriesDataEnum);
		_getCategoriesViaId.executeGetCategoriesViaIdAPI(categoriesId);
	}

	@Then("Verify the category from Db")
	public void verify_the_category_from_Db() {
		_getCategoriesViaId.verifyGetCatgoriesViaIdFromDB();
	}
}
