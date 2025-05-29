package com.drivers.application.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.drivers.application.model.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {
    public Driver findByName(String name);

    @Query("From Driver d where d.onduty=false")
    public ArrayList<Driver> findAvailableDrivers();
}
