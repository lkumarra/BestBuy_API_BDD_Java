package demo.bestbuy.com.products;

import java.util.List;

public class GetProductModal {
	public int total;
	public int limit;
	public int skip;
	public List<GetProductDatum> data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public List<GetProductDatum> getData() {
		return data;
	}

	public void setData(List<GetProductDatum> data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + limit;
		result = prime * result + skip;
		result = prime * result + total;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetProductModal other = (GetProductModal) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (limit != other.limit)
			return false;
		if (skip != other.skip)
			return false;
		if (total != other.total)
			return false;
		return true;
	}
}
