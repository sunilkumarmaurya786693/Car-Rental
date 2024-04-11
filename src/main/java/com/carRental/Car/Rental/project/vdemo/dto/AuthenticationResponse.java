package com.carRental.Car.Rental.project.vdemo.dto;

import com.carRental.Car.Rental.project.vdemo.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private UserRole userRole;
    private Long userid;
}
