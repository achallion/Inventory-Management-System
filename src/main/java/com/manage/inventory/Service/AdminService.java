package com.manage.inventory.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.manage.inventory.Dao.AdminCategoryDao;
import com.manage.inventory.Dao.AdminProductDao;
import com.manage.inventory.Dao.AdminSubCategoryDao;
import com.manage.inventory.Model.Category;
import com.manage.inventory.Model.Product;
import com.manage.inventory.Model.SubCategory;

import javafx.util.Pair;

@Service
public class AdminService {

	@Autowired
	private AdminCategoryDao adminCategoryDao;

	@Autowired
	private AdminSubCategoryDao adminSubCategoryDao;

	@Autowired
	private AdminProductDao adminProductDao;

	//
	public Pair<String, Boolean> addProduct(Pair<Product,Integer> productPair) {
		// Get Product and Parent Sub-Category Id
		Product product = productPair.getKey();
		int parentSubCategoryId = productPair.getValue();

		// Find if Product already present
		Optional<Product> productOptional = adminProductDao.findByName(product.getName());
		if (productOptional.isPresent())
			return new Pair<>("A Product with name " + product.getName() + " is already present.", false);

		// Parent Sub-Category should be present
		Optional<SubCategory> subCategoryOptional = adminSubCategoryDao.findById(parentSubCategoryId);
		if(!subCategoryOptional.isPresent())
			return new Pair<>("No Sub-Category with id " + parentSubCategoryId + " is present.",false);
		product.setSubCategory(subCategoryOptional.get());

		// Try to add Product
		try {
			adminProductDao.save(product);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict: " + e.getMessage(), e);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"INTERNAL_SERVER_ERROR: " + e.getMessage(), e);
		}
		return new Pair<>("Added new Product At ID : " + product.getId(), true);
	}

	//
	public Pair<String, Boolean> addSubCategory(Pair<SubCategory, Integer> subCatPair) {
		// Get SubCategory and Parent Category Id
		SubCategory subCategory = subCatPair.getKey();
		int parentCategoryId = subCatPair.getValue();

		// Find if subcategory already present
		Optional<SubCategory> subCategoryOptional = adminSubCategoryDao.findByName(subCategory.getName());
		if (subCategoryOptional.isPresent())
			return new Pair<>("A Sub-Category with name " + subCategory.getName() + " is already present.", false);

		// Parent Category should be present
		Optional<Category> categoryOptional = adminCategoryDao.findById(parentCategoryId);
		if(!categoryOptional.isPresent())
			return new Pair<>("No Category with id " + parentCategoryId + " is present.",false);
		subCategory.setCategory(categoryOptional.get());

		// Try to add subcategory
		try {
			adminSubCategoryDao.save(subCategory);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict: " + e.getMessage(), e);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal Server Error: " + e.getMessage(), e);
		}
		return new Pair<>("Added new Sub-Category At ID : " + subCategory.getId(), true);
	}

	//
	public Pair<String, Boolean> addCategory(Category category) {
		// Find if Category already present
		Optional<Category> categoryOptional = adminCategoryDao.findByName(category.getName());
		if (categoryOptional.isPresent())
			return new Pair<>("A Category with name " + category.getName() + " is already present.", false);

		// Try to add subcategory
		try {
			adminCategoryDao.save(category);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict: " + e.getMessage(), e);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal Server Error: " + e.getMessage(), e);
		}
		return new Pair<>("Added new Category At ID : " + category.getId(), true);
	}
}
