package com.manage.inventory.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.inventory.Model.SubCategory;

@Repository
public interface AdminSubCategoryDao extends JpaRepository<SubCategory, Integer> {

}
