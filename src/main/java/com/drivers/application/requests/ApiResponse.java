package com.drivers.application.requests;

import java.util.Collection;

import org.springframework.context.annotation.Bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private Object data;
    private String message;
    private Collection<String> errors;
    private boolean status;

    @Override
    public String toString() {
        return "[data=" + data
                + ",\nmessage=" + message
                + ",\nerror=" + errors
                + ",\nstatus=" + status
                + "]";
    }
}
