package demo.bestbuy.com.categories;

import java.util.List;

public class GetCategoryDatum {
	private String id;
	private String name;
	private String createdAt;
	private String updatedAt;
	private List<GetCategorySubCategory> subCategories;
	private List<GetCategoryCategoryPath> categoryPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<GetCategorySubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<GetCategorySubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public List<GetCategoryCategoryPath> getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(List<GetCategoryCategoryPath> categoryPath) {
		this.categoryPath = categoryPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryPath == null) ? 0 : categoryPath.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subCategories == null) ? 0 : subCategories.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		GetCategoryDatum other = (GetCategoryDatum) obj;
		if (categoryPath == null) {
			if (other.categoryPath != null)
				return false;
		} else if (!categoryPath.equals(other.categoryPath))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subCategories == null) {
			if (other.subCategories != null)
				return false;
		} else if (!subCategories.equals(other.subCategories))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}

}
