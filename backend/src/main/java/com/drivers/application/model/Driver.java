package com.drivers.application.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "driverId", unique = true, nullable = false)
    Integer driverId;
    @Column(name = "name", unique = false, nullable = false)
    String name;
    @JsonIgnore
    public Integer getDriverId(){
        return this.driverId;
    }
    public String getName(){
        return this.name;
    }
     @Override 
    public String toString(){
      return "[Object]"  ;
    } 
}
