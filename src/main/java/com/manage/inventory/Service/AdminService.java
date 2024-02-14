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
	public Pair<String, Boolean> addProduct(Product product) {
		Optional<Product> productOptional = adminProductDao.findById(product.getId())
				.or(() -> adminProductDao.findByName(product.getName()));
		if (productOptional.isPresent())
			return new Pair<>("A Product with "
					+ (productOptional.get().getId() == product.getId() ? "id " + product.getId()
							: "name " + product.getName())
					+ " is already present.", false);
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
	public Pair<String, Boolean> addSubCategory(SubCategory subCategory) {
		Optional<SubCategory> subCategoryOptional = adminSubCategoryDao.findById(subCategory.getId())
				.or(() -> adminSubCategoryDao.findByName(subCategory.getName()));
		if (subCategoryOptional.isPresent())
			return new Pair<>("A Sub-Category with "
					+ (subCategoryOptional.get().getId() == subCategory.getId() ? "id " + subCategory.getId()
							: "name " + subCategory.getName())
					+ "is already present.", false);
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

		Optional<Category> categoryOptional = adminCategoryDao.findById(category.getId())
				.or(() -> adminCategoryDao.findByName(category.getName()));
		if (categoryOptional.isPresent())
			return new Pair<>(
					"A Category with "
							+ (categoryOptional.get().getId() == category.getId() ? "id " + category.getId()
									: "name " + category.getName())
							+ " is already present.",
					false);
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
