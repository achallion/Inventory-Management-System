package com.manage.inventory.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manage.inventory.Model.Category;


@Repository
public interface AdminCategoryDao extends JpaRepository<Category, Integer>{

	@Query("SELECT category FROM Category category WHERE category.name = ?1")
	Optional<Category> findByName(String name);
}
