package demo.bestbuy.com.data.stores;

public final class StoresData {

	public enum StoresDataEnum {
		MINNETONKA, INVERGROVERHEIGHTS, ROSEVILLE, BURNSVILLE, INVALID, NONEXISTING
	}

	public static int getStoresData(StoresDataEnum storesDataEnum) {
		switch (storesDataEnum) {
		case MINNETONKA:
			return 4;
		case INVERGROVERHEIGHTS:
			return 6;
		case ROSEVILLE:
			return 7;
		case BURNSVILLE:
			return 8;
		case INVALID:
			return 0;
		case NONEXISTING:
			return 99999;
		default:
			return 4;
		}
	}

}
