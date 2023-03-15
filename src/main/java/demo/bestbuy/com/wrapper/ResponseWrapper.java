package demo.bestbuy.com.wrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ResponseWrapper {

	private String response;
	private int statusCode;
}
