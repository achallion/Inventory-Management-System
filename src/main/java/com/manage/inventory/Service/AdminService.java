package com.manage.inventory.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public String addProduct(Product product) {
		adminProductDao.save(product);
		return "Added At ID : " + product.getId();
	}

	public String addSubCategory(SubCategory subCategory) {
		adminSubCategoryDao.save(subCategory);
		return "Added At ID : " + subCategory.getId();
	}

	public Pair<String, Integer> addCategory(Category category) {

		Optional<Category> categoryOptional = adminCategoryDao.findById(category.getId());
		if (categoryOptional.isPresent())
			return new Pair<String, Integer>(
					"A Category with id " + categoryOptional.get().getId() + " is already present.", 1);
		categoryOptional = Optional.empty();

		categoryOptional = adminCategoryDao.findByName(category.getName());
		if (categoryOptional.isPresent())
			return new Pair<String, Integer>(
					"A Category with name " + category.getName() + " is already present at id " + categoryOptional.get().getId() + ".", 2);

		try {
			adminCategoryDao.save(category);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
		return new Pair<String, Integer>("Added new Category At ID : " + category.getId(), 0);
	}

}
