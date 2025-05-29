package com.drivers.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.InvalidEndpointRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.drivers.application.dao.VehicleRepo;
import com.drivers.application.dto.VehicleDto;
import com.drivers.application.model.Vehicle;
import com.drivers.application.requests.ApiResponse;

@RestController
public class VehicleController {
    @Autowired
    VehicleRepo vehicleRepo;

    // Get ALl Vehicles
    @GetMapping(value = "/driver/api/v1/vehicles")
    @SuppressWarnings("uncheck")
    public List<Vehicle> getVehicles() {
        return vehicleRepo.findAll();
    }

    // Get Vehicle
    @GetMapping("/driver/api/v1/vehicle/{vehicleId}")
    public Vehicle getVehicle(@PathVariable Integer vehicleId) {
        return vehicleRepo.findById(vehicleId).orElseThrow(
                () -> new InvalidEndpointRequestException("Vehicle Not Available on this id : " + vehicleId,
                        "No Vehicle Registered with this vehicle ID"));
    }

    // Create Vehicle
    @PostMapping("/driver/api/v1/vehicle/create")
    public String addVehicle(VehicleDto vehicleProps) {
        System.out.println(vehicleRepo.save(vehicleProps.toVehicle()));
        return "Vehicle Added Successfully";
    }

    // Update Vehicle
    @PutMapping("/driver/api/v1/vehicle/{vehicleId}")
    public String updateVehicle(@PathVariable Integer vehicleId, VehicleDto vehicleProps) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId).orElse(new Vehicle());
        System.out.println(vehicle);
        // Dto Validation Function
        vehicleProps.validate();
        // Updating the Props
        vehicle.setMake(vehicleProps.getMake());
        vehicle.setModal(vehicleProps.getModal());
        vehicle.setRegisterNo(vehicleProps.getRegisterNo());
        vehicle.setImagePath(vehicleProps.getImagePath());
        // Save Operation after updating the properties
        vehicleRepo.save(vehicleProps.toVehicle());
        return "Vehicle Updated Successfull";
    }

    @DeleteMapping(value = "driver/api/v1/vehicle/{vehicleId}")
    public String removeVehicle(@PathVariable Integer vehicleId) {
        vehicleRepo.deleteById(vehicleId);
        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);
        System.out.println();
        if (vehicle.toString() == "Optional.empty")
            return "Delete Successfull";
        else
            return "Delete Operation Failed";
    }

    @PutMapping("/driver/api/v1/vehicle/maintanance/{vehicleId}")
    public String setMaintance(@PathVariable Integer vehicleId) {
        System.out.println(vehicleId);
        return "Vehicle Set maintanance";
    }

    @PostMapping(value = "/driver/api/v1/vehicle/assign-driver/{vehicleId}")
    @ResponseBody
    public ResponseEntity<ApiResponse> assignDriver(@RequestBody VehicleDto vehicleDto,
            @PathVariable Integer vehicleId) {
        return ResponseEntity.ok(ApiResponse.builder().message("Driver Assigned for vehicle " + vehicleId).build());
    }
}
