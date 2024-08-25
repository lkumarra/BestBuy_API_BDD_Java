package demo.bestbuy.com.helpers.responsevalidator;

import demo.bestbuy.com.helpers.interfaces.IResponseValidator;
import demo.bestbuy.com.wrapper.ResponseModalWrapper;
import demo.bestbuy.com.wrapper.ResponseWrapper;
import lombok.Getter;
import lombok.Setter;

public class CategoriesResponseValidator implements IResponseValidator {
    @Getter
    private static ResponseWrapper responseWrapper;

    @Override
    public void setResponseWrapper(ResponseWrapper responseWrapper) {
        CategoriesResponseValidator.responseWrapper = responseWrapper;
    }

    @Override
    public void verifyResponse(ResponseModalWrapper responseModalWrapper) {

    }
}
