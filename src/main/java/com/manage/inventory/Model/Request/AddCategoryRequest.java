package com.manage.inventory.Model.Request;

import com.manage.inventory.Model.Category;

import lombok.Data;

@Data
public class AddCategoryRequest {
	private String name;
	private String description;

	//methods
	public Category getCategory() {
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		return category;
	}
}