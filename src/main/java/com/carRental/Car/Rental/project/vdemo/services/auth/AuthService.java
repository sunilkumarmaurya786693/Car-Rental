package com.carRental.Car.Rental.project.vdemo.services.auth;

import com.carRental.Car.Rental.project.vdemo.dto.SignUpRequest;
import com.carRental.Car.Rental.project.vdemo.dto.UserDto;

public interface AuthService {
    UserDto createCustomer(SignUpRequest signUpRequest);
    boolean hasCustomerHasEmail(String email);
}
