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
	public String addProduct(@RequestBody Product product) {
		return adminService.addProduct(product);
	}

	@PostMapping("add/subcategory")
	public String addSubCategory(@RequestBody SubCategory subCategory) {
		return adminService.addSubCategory(subCategory);
	}

	@PostMapping("add/category")
	public ResponseEntity<String> addCategory(@RequestBody Category category) {
		Pair<String, Integer> p = adminService.addCategory(category);
		if (p.getValue() == 0)
			return new ResponseEntity<>(p.getKey(), HttpStatus.OK);

		String msg = "Failed Request";
		if (p.getValue() == 1 || p.getValue() == 2)
			msg = p.getKey();
		return new ResponseEntity<>(msg, HttpStatus.NOT_MODIFIED);
	}

}
