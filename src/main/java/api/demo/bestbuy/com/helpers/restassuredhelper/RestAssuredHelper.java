package demo.bestbuy.com.restassuredhelper;

import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.bestbuy.com.constants.BDDConstants;
import demo.bestbuy.com.wrapper.ResponseWrapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class contains the method related to GET, POST, PUT and Delete request.
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class RestAssuredHelper {

	/**
	 * Create a Request Specifaction
	 * 
	 * @return RequestSpecification
	 * @author Lavendra rajput
	 */
	private RequestSpecification createRequestSpecification() {
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(BDDConstants.getBaseUri())
				.setContentType(ContentType.JSON).build();
		return requestSpecification;
	}

	/**
	 * Perform Get Rquest
	 * 
	 * @param endPoint EndPoint to perfrom get request
	 * @return ResponseWrapper
	 * @author Lavendra rajput
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
	 * @author Lavendra rajput
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
	 * @author Lavendra rajput
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
	 * @author Lavendra rajput
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
	 * @author Lavendra rajput
	 */
	private ResponseWrapper SetResponseWrapper(Response response) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setResponse(response.asString());
		responseWrapper.setStatusCode(response.getStatusCode());
		return responseWrapper;
	}

	/**
	 * Deserilize the json response
	 * 
	 * @param <T>
	 * @param response : Response to deserialize
	 * @param clazz    :Class
	 * @return
	 * @author Lavendra rajput
	 */
	public <T> T getMappedResponse(String response, Class<T> clazz) {
		try {
			return new ObjectMapper().readValue(response, clazz);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String serializedObject(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		String serializedObject = null;
		try {
			serializedObject = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return serializedObject;
	}
}
