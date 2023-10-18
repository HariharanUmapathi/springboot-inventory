package com.drivers.application.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherService {
    final private static String OPEN_WEATHER_API_KEY = "7cb48abb2df464dbe3158862813570ac";
    final private static String GEO_CODING_API_URL = "http://api.openweathermap.org/geo/1.0/";
    final private static String OPEN_WEATHER_API_3_0 = "https://api.openweathermap.org/data/3.0/";
    final private static String OPEN_WEATHER_API_2_5 = "http://api.openweathermap.org/data/2.5/";

    OpenWeatherService() {

    }

    public String getWeather(String city) {
        // http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=7cb48abb2df464dbe3158862813570ac
        RestTemplate webClient = new RestTemplate();
        String result = webClient.getForObject(
                OPEN_WEATHER_API_2_5 + "weather?q=" + city + ",In&appid=" + OPEN_WEATHER_API_KEY, String.class);
        return result;
    }

    public Object[] getGeoCodingHandle(String city) {
        RestTemplate webClient = new RestTemplate();
        Object[] result = webClient.getForObject(
                GEO_CODING_API_URL + "direct?q=" + city + "&limit=10&" + "&appid=" + OPEN_WEATHER_API_KEY,
                Object[].class);
        return result;
    }
}
