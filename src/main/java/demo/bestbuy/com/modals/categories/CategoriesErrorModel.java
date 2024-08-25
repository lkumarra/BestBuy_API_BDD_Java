package demo.bestbuy.com.modals.categories;

import lombok.Data;

@Data
public class CategoriesErrorModel {
    private String name;
    private String message;
    private int code;
    private String className;
    private Errors errors;

    public static class Errors {

    }
}
