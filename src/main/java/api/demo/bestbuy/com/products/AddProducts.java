package demo.bestbuy.com.products;

import java.text.DateFormat;
import java.util.Date;

import demo.bestbuy.com.apihelper.AssertHelper;
import demo.bestbuy.com.apihelper.InstanceCreator;
import demo.bestbuy.com.apihelper.StringHelper;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.interfaces.IResponseValidator;
import demo.bestbuy.com.products.PostProductModal.PostProduct;
import demo.bestbuy.com.products.ProductModal.GetProductDatum;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;

public class AddProducts extends BaseAPI {

	private PostProduct _postProduct;
	private String _executionTime;
	private GetProductDatum actual;
	private GetProductDatum expected;

	public AddProducts(IResponseValidator responseValidator) {
		super("/products", responseValidator);
	}

	public void executePostProductAPI(String name, String type, int price, String upc, int shipping, String description,
			String manufacturer, String model, String url, String image) {
		_postProduct = new PostProduct();
		_postProduct.setName(name);
		_postProduct.setType(type);
		_postProduct.setPrice(price);
		_postProduct.setUpc(upc);
		_postProduct.setShipping(shipping);
		_postProduct.setDescription(description);
		_postProduct.setManufacturer(manufacturer);
		_postProduct.setModel(model);
		_postProduct.setUrl(url);
		_postProduct.setImage(image);
		String serializedObject = InstanceCreator.getRestAssuredHelperInstace().serializedObject(_postProduct);
		responseWrapper = InstanceCreator.getRestAssuredHelperInstace().performPostRequest(endPoint, serializedObject);
		InstanceCreator.getResponseValidatorInstace().setResponseWrapper(responseWrapper);
		_executionTime = DateFormat.getDateInstance().format(new Date());
	}

	@Override
	public void VerifyResponse(ResponseModalWrapper responseModalWrapper) {
		this.responseValidator.verifyResponse(responseModalWrapper);
		if (responseModalWrapper.getCode() == 201) {
			expected = new GetProductDatum();
			expected.setName(_postProduct.getName());
			expected.setType(_postProduct.getType());
			expected.setPrice(_postProduct.getPrice());
			expected.setShipping(_postProduct.getShipping());
			expected.setUpc(_postProduct.getUpc());
			expected.setDescription(_postProduct.getDescription());
			expected.setManufacturer(_postProduct.getManufacturer());
			expected.setModel(_postProduct.getModel());
			expected.setUrl(_postProduct.getUrl());
			expected.setImage(_postProduct.getImage());
			expected.setCreatedAt(_executionTime);
			expected.setUpdatedAt(_executionTime);
			actual = InstanceCreator.getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
					GetProductDatum.class);
			actual.setCreatedAt(StringHelper.getParsedDate(actual.getCreatedAt()));
			actual.setUpdatedAt(StringHelper.getParsedDate(actual.getUpdatedAt()));
			expected.setId(actual.getId());
			if (!actual.equals(expected)) {
				AssertHelper.AssertFail(actual, expected);
			}
		}
	}

	public void verifyAddedProductFromDB() {
		GetProductDatum dataFromDB = ProductDBHelper.getProductViaId(actual.getId());
		dataFromDB.setCreatedAt(StringHelper.getParsedDate(dataFromDB.getCreatedAt()));
		dataFromDB.setUpdatedAt(StringHelper.getParsedDate(dataFromDB.getUpdatedAt()));
		if (dataFromDB.getCategories().isEmpty()) {
			dataFromDB.setCategories(null);
		}
		if (!expected.equals(dataFromDB)) {
			AssertHelper.AssertFail(dataFromDB, expected);
		}
	}

}
