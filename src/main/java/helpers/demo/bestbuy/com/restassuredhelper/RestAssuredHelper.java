package demo.bestbuy.com.restassuredhelper;

import static io.restassured.RestAssured.*;

import demo.bestbuy.com.constants.BDDConstants;
import demo.bestbuy.com.wrapper.ResponseWrapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredHelper {


	/**
	 * Create a Request Specifaction
	 * 
	 * @return RequestSpecification
	 */
	private RequestSpecification createRequestSpecification() {
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(BDDConstants.baseUri)
				.setContentType(ContentType.JSON).build();
		return requestSpecification;
	}

	/**
	 * Perform Get Rquest
	 * 
	 * @param endPoint EndPoint to perfrom get request
	 * @return ResponseWrapper
	 */
	public ResponseWrapper performGetRequest(String endPoint) {
		Response response = given().spec(createRequestSpecification()).get(endPoint);
		return SetResponseWrapper(response);
	}

	/**
	 * Perform Post Request
	 * 
	 * @param endpoint Endpoint to perfrom post request
	 * @param content  Content to send in body
	 * @return ResponseWrapper
	 */
	public ResponseWrapper performPostRequest(String endpoint, String content) {
		Response response = given().spec(createRequestSpecification()).body(content).post(endpoint);
		return SetResponseWrapper(response);
	}

	/**
	 * Perform Put Request
	 * 
	 * @param endPoint Endpoint to perform put request
	 * @param content  Content to send in body
	 * @return ResponseWrapper
	 */
	public ResponseWrapper performPutRequest(String endPoint, String content) {
		Response response = given().spec(createRequestSpecification()).body(content).put(endPoint);
		return SetResponseWrapper(response);
	}

	/**
	 * Perform Delete Request
	 * 
	 * @param endpoint Endpoint to perform delete request
	 * @return ResponseWrapper
	 */
	public ResponseWrapper performDeleteRequest(String endpoint) {
		Response response = given().spec(createRequestSpecification()).delete(endpoint);
		return SetResponseWrapper(response);
	}

	/**
	 * Set the responsewrapper
	 * 
	 * @param response Response to set
	 * @return ResponseWrapper
	 */
	private ResponseWrapper SetResponseWrapper(Response response) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setResponse(response.asString());
		responseWrapper.setStatusCode(response.getStatusCode());
		return responseWrapper;
	}
}
