package demo.bestbuy.com.testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features = {"classpath:features/categories","classpath:features/products","classpath:features/services"},
		glue = {"demo.bestbuy.com.categories","demo.bestbuy.com.commonsteps","demo.bestbuy.com.hooks","demo.bestbuy.com.products","demo.bestbuy.com.services"}
		)
public class RunCukes {

}
