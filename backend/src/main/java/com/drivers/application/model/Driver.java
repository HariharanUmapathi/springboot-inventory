package com.drivers.application.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

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
@Table(name="DRIVERS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Driver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id", unique = true, nullable = false)
    Integer DriverId;
    @Column(name = "name", unique = false, nullable = false)
    String Name;
    public Driver getDriver(){
        return this;
    }
    public Integer getDriverId(){
        return this.DriverId;
    }
    public String getName(){
        return this.Name;
    }
    /* @Override 
    public String toString(){
        
    } */
}