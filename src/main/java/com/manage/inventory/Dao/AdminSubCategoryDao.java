package com.manage.inventory.Dao;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manage.inventory.Model.SubCategory;

@Repository
public interface AdminSubCategoryDao extends JpaRepository<SubCategory, Integer> {

	@Query("SELECT subCategory FROM SubCategory subCategory where subCategory.name = ?1")
	Optional<SubCategory> findByName(String name);

}
