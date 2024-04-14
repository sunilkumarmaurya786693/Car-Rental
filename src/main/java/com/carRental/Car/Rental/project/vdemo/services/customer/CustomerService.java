package com.carRental.Car.Rental.project.vdemo.services.customer;

import com.carRental.Car.Rental.project.vdemo.dto.BookACarDto;
import com.carRental.Car.Rental.project.vdemo.dto.CarDto;

import java.util.List;

public interface CustomerService {
    List<CarDto> getAllCar();

    boolean bookAcar(BookACarDto bookACarDto);
    CarDto getCarDetails(Long id);

    List<BookACarDto> getBooking(Long user_id);
}
