package com.drivers.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.InvalidEndpointRequestException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivers.application.dao.VehicleRepo;
import com.drivers.application.dto.VehicleDto;
import com.drivers.application.model.Vehicle;

@RestController
public class VehicleController {
    @Autowired
    VehicleRepo vehicleRepo;

    @GetMapping(value = "/driver/api/v1/vehicles")
    @SuppressWarnings("uncheck")
    public List<Vehicle> getVehicles() {
        return vehicleRepo.findAll();
    }

    @GetMapping("/driver/api/v1/vehicle/{vehicleId}")
    public Vehicle getVehicle(@PathVariable Integer vehicleId) {
        return vehicleRepo.findById(vehicleId).orElseThrow(
                () -> new InvalidEndpointRequestException("Vehicle Not Available on this id : " + vehicleId,
                        "No Vehicle Registered with this vehicle ID"));
    }

    @PostMapping("/driver/api/v1/vehicle/create")
    public String addVehicle(VehicleDto vehicleProps) {
        System.out.println(vehicleRepo.save(vehicleProps.toVehicle()));
        return "Vehicle Added Successfully";
    }

    @PutMapping("/driver/api/v1/vehicle/{vehicleId}")
    public String updateVehicle() {
        return "Vehicle Updated Successfull";
    }

    @DeleteMapping(value = "driver/api/v1/vehicle/{vehicleId}")
    public String removeVehicle() {
        return "";
    }

    @PostMapping("/driver/api/v1/vehicle/maintanance/{vehicleId}")
    public String setMaintance(@PathVariable Integer vehicleId) {
        System.out.println(vehicleId);
        return "Vehicle Set maintanance";
    }
}
