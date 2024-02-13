package com.manage.inventory.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manage.inventory.Model.Product;

@Repository
public interface AdminProductDao extends JpaRepository<Product, Integer> {

	@Query("SELECT product FROM Product product WHERE product.name = ?1")
	Optional<Product> findByName(String name);
}
