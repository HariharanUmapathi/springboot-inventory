package com.drivers.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "login";
    }

    @PostMapping("/driver/api/v1/auth")
    @ResponseBody
    public Object authenticate(@RequestBody Map<String, Object> payload) {
        System.out.println("Login Request Receiverd");

        Map<String, Object> message = new HashMap<String, Object>();
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");
        message.put("message", "Login Request Received");
        return message;

    }
}
