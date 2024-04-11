package com.carRental.Car.Rental.project.vdemo.dto;

import com.carRental.Car.Rental.project.vdemo.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private UserRole userRole;
}
