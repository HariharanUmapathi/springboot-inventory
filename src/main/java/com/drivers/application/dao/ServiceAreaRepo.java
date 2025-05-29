package com.drivers.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivers.application.model.ServiceArea;

public interface ServiceAreaRepo extends JpaRepository<ServiceArea, Integer> {

}
