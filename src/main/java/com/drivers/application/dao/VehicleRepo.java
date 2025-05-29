package com.drivers.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drivers.application.model.Vehicle;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

}
