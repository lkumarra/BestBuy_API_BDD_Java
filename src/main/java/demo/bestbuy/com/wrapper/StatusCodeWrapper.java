package demo.bestbuy.com.wrapper;

public final class StatusCodeWrapper {

	public enum StatusCodeEnum {
		OK, NOTFOUND, BADREQUEST, CREATED
	}

	public static int getStatusCodeViaEnum(StatusCodeEnum statusCodeEnum) throws Exception {
		switch (statusCodeEnum) {
		case OK:
			return 200;
		case NOTFOUND:
			return 404;
		case BADREQUEST:
			return 400;
		case CREATED:
			return 201;
		default:
			throw new Exception("Invalid status code");
		}
	}

}
