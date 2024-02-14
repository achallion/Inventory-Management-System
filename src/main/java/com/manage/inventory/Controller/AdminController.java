package com.manage.inventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.inventory.Model.Category;
import com.manage.inventory.Model.Product;
import com.manage.inventory.Model.SubCategory;
import com.manage.inventory.Service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javafx.util.Pair;

@RequestMapping("admin")
@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("add/product")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		Pair<String, Boolean> result = adminService.addProduct(product);
		HttpStatus status = result.getValue() == true ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity<>(result.getKey(), status);
	}

	@PostMapping("add/subcategory")
	public ResponseEntity<String> addSubCategory(@RequestBody SubCategory subCategory) {
		Pair<String, Boolean> result = adminService.addSubCategory(subCategory);
		HttpStatus status = result.getValue() == true ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity<>(result.getKey(), status);
	}

	@PostMapping("add/category")
	public ResponseEntity<String> addCategory(@RequestBody Category category) {
		Pair<String, Boolean> result = adminService.addCategory(category);
		HttpStatus status = result.getValue() == true ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity<>(result.getKey(), status);
	}

}
