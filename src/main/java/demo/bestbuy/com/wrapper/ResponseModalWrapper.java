package demo.bestbuy.com.wrapper;

import lombok.Data;

@Data
public final class ResponseModalWrapper {

	private String name;
	private String message;
	private int code;
	private String errors;

}
