package demo.bestbuy.com.wrapper;

import lombok.Data;

@Data
public final class ResponseWrapper {
	private String response;
	private int statusCode;
}
