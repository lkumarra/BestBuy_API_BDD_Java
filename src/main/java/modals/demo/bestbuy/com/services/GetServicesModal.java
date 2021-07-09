package demo.bestbuy.com.services;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class GetServicesModal {
    private int total;
    private int limit;
    private int skip;
    private List<Datum> data;
}
