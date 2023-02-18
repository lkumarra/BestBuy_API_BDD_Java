package demo.bestbuy.com.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "classpath:features/categories", "classpath:features/products",
		"classpath:features/services","classpath:features/stores" }, glue = { "demo.bestbuy.com.categories", "demo.bestbuy.com.commonsteps",
				"demo.bestbuy.com.hooks", "demo.bestbuy.com.products", "demo.bestbuy.com.services","demo.bestbuy.com.stores" }, plugin = {
						"json:target/cucumber.json", "html:target/site/cucumber-pretty.html",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class TestNgRunner extends AbstractTestNGCucumberTests {

}
