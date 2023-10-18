package com.drivers.application.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.drivers.application.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
