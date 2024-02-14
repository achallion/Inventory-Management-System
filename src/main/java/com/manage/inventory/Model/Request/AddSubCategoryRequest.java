package com.manage.inventory.Model.Request;

import com.manage.inventory.Model.SubCategory;

import javafx.util.Pair;
import lombok.Data;

@Data
public class AddSubCategoryRequest {
	private String name;
	private String description;
	private int parentCategoryId;

	// methods
	public Pair<SubCategory,Integer> getSubCategoryPair() {
		SubCategory subCategory = new SubCategory();
		subCategory.setName(name);
		subCategory.setDescription(description);
		return new Pair<>(subCategory, parentCategoryId);
	}
}
