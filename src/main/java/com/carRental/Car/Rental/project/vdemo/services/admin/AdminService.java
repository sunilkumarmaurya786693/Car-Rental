package com.carRental.Car.Rental.project.vdemo.services.admin;

import com.carRental.Car.Rental.project.vdemo.dto.BookACarDto;
import com.carRental.Car.Rental.project.vdemo.dto.CarDto;
import com.carRental.Car.Rental.project.vdemo.dto.CarDtoListDto;
import com.carRental.Car.Rental.project.vdemo.dto.SearchCarDto;
import com.carRental.Car.Rental.project.vdemo.entity.Car;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCar();

    boolean deleteCar(Long id);

    CarDto getCarDetails(Long id);

    boolean updateCar(Long id, CarDto carDetails) throws IOException;

    List<BookACarDto> getAllCarBooking();

    Boolean changeBookingStatus(Long bookingId, String status);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);
}
