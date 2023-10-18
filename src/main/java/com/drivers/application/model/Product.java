package com.drivers.application.model;

import java.util.Date;

import jakarta.persistence.Column;
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
@Table(name="Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productId;
    @Column(name="name")
    String name;
    @Column(name="sale_price")
    Float salesPrice;
    @Column(name="purchase_price")
    Float purchasePrice;
    @Column(name="tax_included")
    Boolean taxIncluded;
    @Column(name="opening_stock")
    Float openingStock;
    @Column(name="minimum_stock")
    Float mininumStock;
    @Column(name="stock_date")
    Date stockDate;
    @Column(name="hsn_code")
    Integer hsn;
    @Column(name="cgst")
    Float cgst;
    @Column(name="sgst")
    Float sgst;
    @Column(name="cess")
    Float cess;
    public Integer getProduct(){
        return this.productId;
    }
    public String getName(){
        return this.name;
    }
    @Override 
    public String toString(){
      return "Product {"+this.productId+""+this.name+"}";
    } 
}
