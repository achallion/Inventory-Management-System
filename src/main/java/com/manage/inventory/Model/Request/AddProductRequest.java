package com.manage.inventory.Model.Request;

import com.manage.inventory.Model.Product;

import javafx.util.Pair;
import lombok.Data;

@Data
public class AddProductRequest {
	private String name;
	private String description;
	private int price;
	private int quantity;
	private int parentSubCategoryId;

	// methods
	public Pair<Product, Integer> getProductPair() {
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		return new Pair<>(product, parentSubCategoryId);
	}
}
