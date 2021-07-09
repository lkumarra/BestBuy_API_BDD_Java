package demo.bestbuy.com.services;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Datum {
	private int id;
	private String name;
	private String createdAt;
	private String updatedAt;
}
