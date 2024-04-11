package com.carRental.Car.Rental.project.vdemo.services.admin;

import com.carRental.Car.Rental.project.vdemo.dto.CarDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCar();

    boolean deleteCar(Long id);
}
