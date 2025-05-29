package com.drivers.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivers.application.dao.BookingRepo;
import com.drivers.application.dao.DriverRepo;
import com.drivers.application.dao.UserRepo;
import com.drivers.application.dao.VehicleRepo;

@RestController
public class DashboardController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    DriverRepo driverRepo;
    @Autowired
    VehicleRepo vehicleRepo;
    @Autowired
    BookingRepo bookingRepo;

    @GetMapping("/driver/api/v1/dashboard/get-stats/")
    public Map<String, Object> getStats() {
        Map<String, Object> stat = new HashMap<>();
        stat.put("bookings", bookingRepo.count());
        stat.put("drivers", driverRepo.count());
        stat.put("vehicles", vehicleRepo.count());
        stat.put("users", userRepo.count());
        return stat;
    }
}
