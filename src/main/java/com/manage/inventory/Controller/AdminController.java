package com.manage.inventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.manage.inventory.Model.Product;
import com.manage.inventory.Model.Request.AddCategoryRequest;
import com.manage.inventory.Model.Request.AddProductRequest;
import com.manage.inventory.Model.Request.AddSubCategoryRequest;
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
	public ResponseEntity<String> addProduct(@RequestBody AddProductRequest request) {
		if(request == null || request.getName() == null || request.getDescription() == null || request.getParentSubCategoryId() == 0 )
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Check Request Body Params");
		Pair<String, Boolean> result = adminService.addProduct(request.getProductPair());
		HttpStatus status = result.getValue() == true ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity<>(result.getKey(), status);
	}

	@PostMapping("add/subcategory")
	public ResponseEntity<String> addSubCategory(@RequestBody AddSubCategoryRequest request) {
		if(request == null || request.getName() == null || request.getDescription() == null || request.getParentCategoryId() == 0)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Check Request Body Params");
		Pair<String, Boolean> result = adminService.addSubCategory(request.getSubCategoryPair());
		HttpStatus status = result.getValue() == true ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity<>(result.getKey(), status);
	}

	@PostMapping("add/category")
	public ResponseEntity<String> addCategory(@RequestBody AddCategoryRequest request) {
		if(request == null || request.getName() == null || request.getDescription() == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Check Request Body Params");
		Pair<String, Boolean> result = adminService.addCategory(request.getCategory());
		HttpStatus status = result.getValue() == true ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity<>(result.getKey(), status);
	}

}
