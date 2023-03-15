package demo.bestbuy.com.wrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper {

	private String response;
	private int statusCode;
}
