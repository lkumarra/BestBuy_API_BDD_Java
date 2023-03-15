package demo.bestbuy.com.data.services;

public final class ServicesData {

	public enum ServicesDataEnum {
		Geek_Squad_Services, Best_Buy_Mobile, Best_Buy_for_Business, Invalid, NotExisting
	}

	public static int getServicesData(ServicesDataEnum servicesDataEnum) {
		switch (servicesDataEnum) {
		case Geek_Squad_Services:
			return 1;
		case Best_Buy_Mobile:
			return 2;
		case Best_Buy_for_Business:
			return 3;
		case Invalid:
			return 0;
		case NotExisting:
			return 9999;
		default:
			return 0;
		}
	}
}
