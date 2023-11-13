package com.drivers.application.dto;

import java.util.Set;

import com.drivers.application.model.Vehicle;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class VehicleDto {
    private boolean assigned;
    private boolean maintainance;
    @NotBlank(message = "Register No Should Not Blank")
    @NotEmpty(message = "Register No Should Not Empty")
    private String registerNo;
    private String type;
    private String imagePath;
    private String make;
    private String modal;

    public Vehicle toVehicle() {
        return new Vehicle()
                .setMake(make)
                .setModal(modal)
                .setType(type)
                .setRegisterNo(registerNo)
                .setImagePath("")
                .setDriverId(0)
                .setAssigned(false)
                .setMaintanance(false);
    }

    public void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<VehicleDto>> violations = validator.validate(this);
        if (violations.size() > 0)
            throw new ConstraintViolationException(violations);
    }
}
