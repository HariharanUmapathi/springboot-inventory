package com.drivers.application.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenRouteService {
    @Value("${openroute.apikey}")
    private String OPEN_ROUTE_API_KEY;
    final static private String OPEN_ROUTE_URL = "https://api.openrouteservice.org";

    OpenRouteService() {

    }

    public String getDirections(String Slatitude, String Slongitude, String Elatitude, String Elongitude) {
        RestTemplate webClient = new RestTemplate();
        String result = webClient.getForObject(
                OPEN_ROUTE_URL + "/v2/directions/driving-car?api_key=" + OPEN_ROUTE_API_KEY +
                        "&start=" + Slatitude + "," + Slongitude +
                        "&end=" + Elatitude + "," + Elongitude,
                String.class);
        return result;
    }
}
