package com.drivers.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.drivers.application.dao.DriverRepo;
import com.drivers.application.model.Driver;

@RestController
public class DriverController {
    @Autowired
    DriverRepo repo;
    @GetMapping("/list-drivers-json")
    public List<Driver> list_drivers_json(){
        return repo.findAll();
    }
    @GetMapping("/list-drivers")
    public ModelAndView list_drivers() {
        ModelAndView mv = new ModelAndView();
        System.out.println("IN Controller " + repo.findAll());
        mv.addObject("drivers", repo.findAll());
        mv.addObject("data","Data test");
        mv.setViewName("DriversList");
        return mv;
    }

    @PostMapping("/add-driver")
    public ModelAndView add_driver(@ModelAttribute("Name") Driver driver) {
        System.out.println(driver);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddDriverForm");
        return mv;
    }

    @GetMapping("/add-driver")
    public ModelAndView show_driver(Integer DriverId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddDriverForm");
        return mv;
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
}
