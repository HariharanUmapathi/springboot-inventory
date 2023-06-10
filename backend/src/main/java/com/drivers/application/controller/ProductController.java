package com.drivers.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drivers.application.dao.ProductRepo;
import com.drivers.application.model.Product;

@RestController
public class ProductController {
    @Autowired
    ProductRepo productRepo;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProduct(@PathVariable Integer id) {
        return productRepo.findById(id);
    }

    @PostMapping("/product")
    public HashMap<String, Object> addProduct(@RequestParam Map<String,String> requestParams) {
        System.out.println(requestParams.entrySet());
        // Using Has map for Response sending purpose
        HashMap<String, Object> map = new HashMap<>();
        try {
            map.put("status", true);
            map.put("message", "Added Product Successfully");
            return map;
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", "Failed to Add Product");
            return map;
        }

    }

    @PutMapping("/product/{id}")
    public HashMap<String, Object> updateProduct(@PathVariable Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            map.put("status", true);
            map.put("message", "Product Updated Successfully");
            return map;
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", "Failed to Update Product");
            return map;
        }
    }

    @DeleteMapping("/product/{id}")
    public HashMap<String, Object> deleteProduct(@PathVariable Integer id) {
        System.out.println("Id Received " + id);
        HashMap<String, Object> map = new HashMap<>();
        try {
            map.put("status", true);
            map.put("message", "Deleted Product Successfully");
            return map;
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", "Failed to Delete Product");
            return map;
        }
    }
}
