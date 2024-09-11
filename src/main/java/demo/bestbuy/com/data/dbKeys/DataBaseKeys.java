package demo.bestbuy.com.data.dbKeys;

public final class DataBaseKeys {

    public static class CategoriesTableKeys {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String CREATED_AT = "createdAt";
        public static final String UPDATED_AT = "updatedAt";
    }

    public static class SubCategoriesTableKeys {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String CREATED_AT = "createdAt";
        public static final String UPDATED_AT = "updatedAt";
    }

    public static class CategoryPathTableKeys {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String CREATED_AT = "createdAt";
        public static final String UPDATED_AT = "updatedAt";
    }

    public static class ProductTableKeys {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TYPE = "type";
        public static final String PRICE = "price";
        public static final String UPC = "upc";
        public static final String SHIPPING = "shipping";
        public static final String DESCRIPTION = "description";
        public static final String MANUFACTURER = "manufacturer";
        public static final String MODEL = "model";
        public static final String URL = "url";
        public static final String IMAGE = "image";
        public static final String CREATED_AT = "createdAt";
        public static final String UPDATED_AT = "updatedAt";
    }

    public static class ServicesTableKeys {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String CREATED_AT = "createdAt";
        public static final String UPDATED_AT = "updatedAt";
    }

    public static class StoresTableKeys {
        public static final String STORES_ID = "storeId";
        public static final String SERVICES_ID = "serviceId";
        public static final String STORES_NAME = "storesName";
        public static final String TYPE = "type";
        public static final String ADDRESS = "address";
        public static final String ADDRESS2 = "address2";
        public static final String HOURS = "hours";
        public static final String CITY = "city";
        public static final String STATE = "state";
        public static final String ZIP = "zip";
        public static final String LAT = "lat";
        public static final String LNG = "lng";
        public static final String STORES_CREATED_AT = "storesCreatedAt";
        public static final String STORES_UPDATED_AT = "storesUpdatedAt";
        public static final String SERVICES_NAME = "servicesName";
        public static final String SERVICE_UPDATED_AT = "serviceUpdatedAt";
        public static final String SERVICES_CREATED_AT = "serviceCreatedAt";
        public static final String STORES_SERVICE_CREATED_AT = "storeServiceCreatedAt";
        public static final String STORES_SERVICE_UPDATED_AT = "storeServiceUpdatedAt";
    }

}
