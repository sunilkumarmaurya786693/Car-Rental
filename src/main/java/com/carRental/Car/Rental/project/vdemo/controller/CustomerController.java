package com.carRental.Car.Rental.project.vdemo.controller;

import com.carRental.Car.Rental.project.vdemo.dto.BookACarDto;
import com.carRental.Car.Rental.project.vdemo.dto.CarDto;
import com.carRental.Car.Rental.project.vdemo.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/getallcars")
    public ResponseEntity<?> getAllCars(){
        System.out.println("Sfsdf");
        return ResponseEntity.ok(customerService.getAllCar());
    }

    @PostMapping("/booking")
    public ResponseEntity<?> bookingCar(@RequestBody BookACarDto bookACarDto){
        boolean success = customerService.bookAcar(bookACarDto);
        if(success)return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto>getCarDetails(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCarDetails(id));
    }

    @GetMapping("/booking/{userId}")
    public ResponseEntity<List<BookACarDto>>getBookings(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getBooking(userId));
    }
}
