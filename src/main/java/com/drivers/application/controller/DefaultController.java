package com.drivers.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivers.application.services.ExcelImportService;

@RestController
public class DefaultController {
    @Autowired
    ExcelImportService excelImportService;

    @GetMapping("/")
    public Map<String, Object> index() {
        Map<String, Object> applicationURIS = new HashMap<String, Object>();
        applicationURIS.put("DriverApplication", "/driver/*");
        applicationURIS.put("InventoryApplication", "/inventory/*");
        applicationURIS.put("WeaterApplication", "/weather/*");
        Map<String, Object> applicationInfo = new HashMap<String, Object>();
        applicationInfo.put("name", "Spring Boot Applications Collection");
        applicationInfo.put("version", "1.0.0");
        applicationInfo.put("author", "Hariharan U");
        applicationInfo.put("applications", "DriverApplication," +
                "InventoryApplication," +
                "WeatherApplication");
        applicationInfo.put("api_URI", applicationURIS);
        return applicationInfo;
    }

    @GetMapping("/locations-import")
    public String locations_import() {
        excelImportService.importServiceAreas();
        return excelImportService.doNothing();
    }
}
