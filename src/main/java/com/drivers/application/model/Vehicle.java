package com.drivers.application.model;

import java.io.Serializable;

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
@Table(name = "Vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicleId", unique = true, nullable = false)
    Integer vehicleId;
    @Column(name = "registerNo", unique = false, nullable = false)
    String name;
    @Column(name = "imagePath", unique = false, nullable = false)
    String year;
    @Column(name = "driverId", unique = false, nullable = false)
    Integer driverId;
}
