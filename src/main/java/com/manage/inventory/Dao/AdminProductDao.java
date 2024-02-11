package com.manage.inventory.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.inventory.Model.Product;

@Repository
public interface AdminProductDao extends JpaRepository<Product, Integer> {

}
