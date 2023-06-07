package com.drivers.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivers.application.dao.DriverRepo;
import com.drivers.application.model.*;
@RestController
public class HelloController {
	@Autowired
	DriverRepo repo;
	@GetMapping("/")
	public String index() {
		Driver driver=new Driver();
		//repo.deleteAll();
		driver.setName("Hariharan");
		repo.save(driver);
		driver=new Driver();
		driver.setDriverId(2);
		driver.setName("Umapathi");
		repo.save(driver);
		return "Spring Boot Inventory Management System Version 1.0.0";
	}
	@GetMapping("/list-drivers-hello")
	public List<Driver> list(){
		return repo.findAll();
	}
	@GetMapping("/test")
	public String test(){
		return "Sending Message from Text";
	}
	@GetMapping("/jsontest")
	public Driver sendDriverJson(){
		Driver driver=new Driver(2,"Hariharan");
		return driver;
	}
	@GetMapping("/error")
	public String errorHandler(){
		
		return "Error Handler";
	}
}
