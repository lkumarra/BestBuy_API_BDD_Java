package demo.bestbuy.com.products;
import java.text.DateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import demo.bestbuy.com.helpers.apihelper.*;
import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.helpers.products.ProductDBHelper;
import demo.bestbuy.com.modals.products.PostProductModal.PostProduct;
import demo.bestbuy.com.modals.products.ProductModal.GetProductDatum;
import static demo.bestbuy.com.helpers.apihelper.InstanceCreator.*;
import demo.bestbuy.com.baseapi.BaseAPI;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;

@Slf4j
public final class AddProducts extends BaseAPI {

	private PostProduct postProduct;
	private String executionTime;
	private GetProductDatum actual;
	private GetProductDatum expected;

	public AddProducts(IResponseValidator responseValidator) {
		super("/products", responseValidator);
	}

	public void executePostProductAPI(String name, String type, int price, String upc, int shipping, String description,
			String manufacturer, String model, String url, String image) {
		postProduct = new PostProduct();
		postProduct.setName(name);
		postProduct.setType(type);
		postProduct.setPrice(price);
		postProduct.setUpc(upc);
		postProduct.setShipping(shipping);
		postProduct.setDescription(description);
		postProduct.setManufacturer(manufacturer);
		postProduct.setModel(model);
		postProduct.setUrl(url);
		postProduct.setImage(image);
		String serializedObject = getRestAssuredHelperInstace().serializedObject(postProduct);
		responseWrapper = getRestAssuredHelperInstace().performPostRequest(endPoint, serializedObject);
		log.warn("Response for endpoint : {} with request : {} with method : {} is : {} ", endPoint,
				serializedObject, "POST", responseWrapper.getResponse());
		getResponseValidatorInstace(responseValidator).setResponseWrapper(responseWrapper);
		executionTime = DateFormat.getDateInstance().format(new Date());
	}

	@Override
	public void VerifyResponse(ResponseModalWrapper responseModalWrapper) {
		this.responseValidator.verifyResponse(responseModalWrapper);
		if (responseModalWrapper.getCode() == 201) {
			expected = new GetProductDatum();
			expected.setName(postProduct.getName());
			expected.setType(postProduct.getType());
			expected.setPrice(postProduct.getPrice());
			expected.setShipping(postProduct.getShipping());
			expected.setUpc(postProduct.getUpc());
			expected.setDescription(postProduct.getDescription());
			expected.setManufacturer(postProduct.getManufacturer());
			expected.setModel(postProduct.getModel());
			expected.setUrl(postProduct.getUrl());
			expected.setImage(postProduct.getImage());
			expected.setCreatedAt(executionTime);
			expected.setUpdatedAt(executionTime);
			actual = getRestAssuredHelperInstace().getMappedResponse(responseWrapper.getResponse(),
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
