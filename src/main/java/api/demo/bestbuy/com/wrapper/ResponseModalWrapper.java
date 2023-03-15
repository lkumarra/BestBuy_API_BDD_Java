package demo.bestbuy.com.wrapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ResponseModalWrapper {

	private String name;
	private String message;
	private int code;
	private String errors;

}
