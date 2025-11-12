package com.drivers.application.services;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RandomUserService {
    final private static String RANDOM_USER_API_URL = "https://randomuser.me/api/?inc=gender,name,nat,picture&results=1000";
    final private static String RANDOM_USER_DRIVERS_COLLECTION = "https://randomuser.me/api/?inc=name,gender&results=250";
    @SuppressWarnings("unchecked")
    public Map<String, Object> getUserData() {
        RestTemplate webClient = new RestTemplate();
        Map<String, Object> result = webClient.getForObject(RANDOM_USER_API_URL, Map.class);
        return result;
    }
    @SuppressWarnings("unchecked")
    public Map<String,Object>getDrivers(){
        RestTemplate webClient = new RestTemplate();
        Map<String,Object> result = webClient.getForObject(RANDOM_USER_DRIVERS_COLLECTION,Map.class);
        return result;
    }

}
