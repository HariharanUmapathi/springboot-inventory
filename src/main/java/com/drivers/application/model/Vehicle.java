package com.drivers.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vehicle")
@Accessors(chain = true)
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicleId", unique = true, nullable = false)
    Integer vehicleId;
    @Column(name = "registerNo", unique = true, nullable = false)
    String registerNo;
    @Column(name = "make")
    String make;
    @Column(name = "modal")
    String modal;
    @Column(name = "imagePath", unique = false, nullable = false)
    String imagePath;
    @Column(name = "driverId", unique = false, nullable = false)
    Integer driverId;
    @Column(name = "assigned", unique = false, nullable = false)
    Boolean assigned;
    @Column(name = "maintainance", unique = false, nullable = false)
    Boolean maintanance;
    @Column(name = "type", unique = false, nullable = false)
    String type;
}
