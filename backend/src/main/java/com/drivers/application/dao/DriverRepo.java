package com.drivers.application.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.drivers.application.model.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

}
