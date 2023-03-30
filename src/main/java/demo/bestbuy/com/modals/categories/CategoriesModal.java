package demo.bestbuy.com.modals.categories;

import java.util.List;

import lombok.Data;

public final class CategoriesModal {
    @Data
    public static class GetCategoryModal {
        private int total;
        private int limit;
        private int skip;
        private List<GetCategoryDatum> data;

    }

    @Data
    public static class GetCategoryDatum {
        private String id;
        private String name;
        private String createdAt;
        private String updatedAt;
        private List<GetCategorySubCategory> subCategories;
        private List<GetCategoryCategoryPath> categoryPath;
    }

    @Data
    public static class GetCategoryCategoryPath {
        private String id;
        private String name;
        private String createdAt;
        private String updatedAt;
    }

    @Data
    public static class GetCategorySubCategory {
        private String id;
        private String name;
        private String createdAt;
        private String updatedAt;
    }

}
