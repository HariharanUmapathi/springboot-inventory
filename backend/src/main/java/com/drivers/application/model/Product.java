package com.drivers.application.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ProductId;
    String Name;
    public Integer getProduct(){
        return this.ProductId;
    }
    public String getName(){
        return this.Name;
    }
    @Override 
    public String toString(){
      return "Product {"+this.ProductId+""+this.Name+"}";
    } 
}
