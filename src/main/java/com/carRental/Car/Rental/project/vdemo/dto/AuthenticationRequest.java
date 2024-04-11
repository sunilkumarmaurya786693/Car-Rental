package com.carRental.Car.Rental.project.vdemo.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
