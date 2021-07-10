package demo.bestbuy.com.hooks;

import demo.bestbuy.com.apihelper.APIHelper;
import demo.bestbuy.com.products.ProductDBHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * This class contains all the Hooks.
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class Hooks {

	@Before(order = 1)
	public void startApiServer() {
		APIHelper.startApiServer();
	}

	@After
	public void stopServer() {
		APIHelper.startApiServer();
	}
	
	@Before(value = "@PostProducts")
	public void deleteAddedProduct() {
		ProductDBHelper.deleteAddedProduct();
	}
}
