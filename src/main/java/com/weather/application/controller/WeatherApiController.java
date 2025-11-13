package com.weather.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.weather.application.services.OpenWeatherService;

@RestController
class WeatherApiController {
    @Autowired
    OpenWeatherService service;

    @GetMapping("/getWeather/{city}")
    public String getWeather(@PathVariable String city) {
        return service.getWeather(city);
    }
    @GetMapping("/test/{test}")
    public String getTest(@PathVariable String test){
        System.out.println(test);
        return test;
    }
    @GetMapping("/weather/{test}")
    public String weatherHome(@PathVariable String test){
        return "Hi from controller weather";
    }
    @GetMapping("/error")
    public String genericErrorMessage(){
        return "Something Went wrong Please bear with Smile we will be back soon";
    }
}