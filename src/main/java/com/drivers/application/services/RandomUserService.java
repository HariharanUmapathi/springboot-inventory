package com.drivers.application.services;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RandomUserService {
    final private static String RANDOM_USER_API_URL = "https://randomuser.me/api/?inc=gender,name,nat,picture&results=1000";

    public Map<String, Object> getUserData() {
        RestTemplate webClient = new RestTemplate();
        Map<String, Object> result = webClient.getForObject(RANDOM_USER_API_URL, Map.class);
        return result;
    }

}
