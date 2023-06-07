package com.drivers.application.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.drivers.application.dao.DriverRepo;
import com.drivers.application.model.Driver;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DriverController {
    @Autowired
    DriverRepo repo;
    @GetMapping("/list-drivers-json")
    @ResponseBody
    public String list_drivers_json(){
        try {
            ObjectMapper obmapper=new ObjectMapper();
            List<Driver> drivers=repo.findAll();
            String JsonString="";
            Iterator<Driver> driverIterator=drivers.iterator();
            while(driverIterator.hasNext()){
                JsonString+=obmapper.writeValueAsString(driverIterator.next());
            }
            return JsonString;
        }catch(Exception e){
            return "[object Object]";
        }
        
    }
    @GetMapping("/list-drivers")
    public String list_drivers() {
        return "IN Controller " + repo.findAll();
        //return "HI";
        //return mv;
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
}
