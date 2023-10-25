package com.drivers.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.drivers.application.dao.request.SignUpRequest;
import com.drivers.application.dao.request.SigninRequest;
import com.drivers.application.dao.response.JwtAuthenticationResponse;
import com.drivers.application.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/driver/api/v1/auth")
    @ResponseBody
    public ResponseEntity<JwtAuthenticationResponse> authenticate(SigninRequest signinRequest) {
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/driver/api/v1/signup")
    @ResponseBody
    public ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest signupRequest) {
        return ResponseEntity.ok(authenticationService.signup(signupRequest));
    }
}
