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

}