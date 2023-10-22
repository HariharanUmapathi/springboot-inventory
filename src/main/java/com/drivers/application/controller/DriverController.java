package com.drivers.application.controller;

import java.util.Map;
import java.util.HashMap;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.drivers.application.dao.DriverRepo;
import com.drivers.application.dao.UserRepo;
import com.drivers.application.model.Driver;
import com.drivers.application.model.User;
import com.drivers.application.services.OpenRouteService;
import com.drivers.application.services.RandomUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class DriverController {
    @Autowired
    RandomUserService randomUserService;
    @Autowired
    OpenRouteService openRouteService;
    @Autowired
    DriverRepo repo;
    @Autowired
    UserRepo userRepo;

    final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @GetMapping("/driver/")
    public String index() {
        logger.error("Responed Success");
        return "Spring Boot Taxi Management System Version 1.0.0";
    }

    @PostMapping("/driver/api/v1/user/create")
    @ResponseBody
    public Object createUser(@RequestBody Map<String, Object> payload) {
        try {
            User user = new User();
            user.setName((String) payload.get("username"));
            user.setPassword((String) payload.get("password"));
            userRepo.save(user);
            Map<String, Object> message = new HashMap<String, Object>();
            message.put("message", "Application Down");
            message.put("errorCode", "500");
            return message;
        } catch (NoSuchAlgorithmException e) {
            Map<String, Object> message = new HashMap<String, Object>();
            message.put("message", "Application Down");
            message.put("errorCode", "500");

            logger.debug("NoSuchAlgorithmException thrown");
            return message;
        }

    }

    @GetMapping("/driver/api/v1/drivers")
    @ResponseBody
    public List<Driver> list_drivers_json() {
        try {
            List<Driver> drivers = repo.findAvailableDrivers();
            return drivers;
        } catch (Exception e) {
            List<Driver> drivers = new ArrayList<Driver>();
            return drivers;
        }
    }

    @GetMapping("/driver/api/import-data")
    @ResponseBody
    public Map<String, Object> import_data() {
        return randomUserService.getUserData();
    }

    @GetMapping("/driver/api/v1/driver/{:id}/profile")
    @ResponseBody
    public Map<String, Object> display_profile() {
        Map<String, Object> data = new HashMap<String, Object>();
        return data;
    }

    @GetMapping("/driver/api/v1/driver/{:id}")
    @ResponseBody
    public Driver list_driver_json(@PathVariable int id) {
        try {
            System.out.println(id);
            return repo.getReferenceById(id);
        } catch (Exception e) {
            return new Driver();
        }
    }

    @GetMapping("/list-drivers")
    public String list_drivers() {
        repo.findAll();
        return "IN Controller ";
    }

    @PostMapping("/add-driver")
    public ModelAndView add_driver(@ModelAttribute("Name") Driver driver) {
        System.out.println(driver);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddDriverForm");
        return mv;
    }

    @GetMapping("/add-driver")
    public String show_driver(Integer DriverId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddDriverForm");
        return "Test";
    }

    @GetMapping("/update-driver/{:id}")
    public ModelAndView update_driver() {
        System.out.println("UPdate driver GET controller Called");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("UpdateDriverForm");
        return mv;
    }

    @PostMapping("/update-driver/{:id}/")
    public ModelAndView save_driver(@ModelAttribute("Name") Driver driver) {
        System.out.println("UPdate driver POST controller Called");
        ModelAndView mv = new ModelAndView();
        mv.addObject("updatevalue", repo.save(driver));
        return mv;
    }

    @PostMapping("/driver/api/v1/booking-directions")
    public String booking_directions(@RequestBody Map<String, Object> payload) {
        try {
            return openRouteService.getDirections((String) payload.get("sLatitude"),
                    (String) payload.get("sLongitude"), (String) payload.get("eLatitude"),
                    (String) payload.get("eLongitude"));
        } catch (ClassCastException e) {
            logger.info("Type Conversion error", e);
            return "ERROR: Invalid Parameter";
        } catch (HttpClientErrorException e) {
            logger.info("HTTP Client Error", e);
            return "ERROR: Invalid Client Request";
        }

    }
}
