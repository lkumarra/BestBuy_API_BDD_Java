package demo.bestbuy.com.testrunner;

import org.junit.runner.RunWith;

import courgette.api.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/categories", glue = { "demo\\bestbuy\\com\\categories" })
public class TestRunner {

}
