package com.inventory.application.dao;

import org.springframework.stereotype.Repository;

import com.inventory.application.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
