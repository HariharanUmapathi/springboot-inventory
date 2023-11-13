package com.drivers.application.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.validation.ConstraintViolationException;

import org.springframework.boot.actuate.endpoint.InvalidEndpointRequestException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.drivers.application.requests.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();

        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @Order(1)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(cv -> errors.put("msg", cv.getMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Order(2)
    @ExceptionHandler(InvalidEndpointRequestException.class)
    public ResponseEntity<?> invalidEndpointRequestException(InvalidEndpointRequestException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @Order(3)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> DataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("errors", "Got Error in our service please try again after some time");
        result.put("exception", ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> BadCredentialsException(BadCredentialsException ex, WebRequest request) {
        /*
         * Map<String, Object> response = new HashMap<>();
         * response.put("message", "Invalid Username Or Password");
         * response.put("status", false);
         */

        ApiResponse response = ApiResponse
                .builder()
                .message("Invalid Username Or Password")
                .status(false)
                .build();
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<?> Exception(Exception ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>("Somthing Went Wrong", HttpStatus.SERVICE_UNAVAILABLE);
    }

}