package demo.bestbuy.com.testrunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"classpath:features/categories","classpath:features/products","classpath:features/services"},
		glue = {"demo.bestbuy.com.categories","demo.bestbuy.com.commonsteps","demo.bestbuy.com.hooks","demo.bestbuy.com.products","demo.bestbuy.com.services"}, plugin = {"json:target/cucumber.json","html:target/site/cucumber-pretty.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		tags = "@Categories"
		)
public class TestNgRunner extends AbstractTestNGCucumberTests {

	
}
