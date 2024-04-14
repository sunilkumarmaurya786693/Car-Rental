package com.carRental.Car.Rental.project.vdemo.controller;

import com.carRental.Car.Rental.project.vdemo.dto.BookACarDto;
import com.carRental.Car.Rental.project.vdemo.dto.CarDto;
import com.carRental.Car.Rental.project.vdemo.dto.SearchCarDto;
import com.carRental.Car.Rental.project.vdemo.entity.Car;
import com.carRental.Car.Rental.project.vdemo.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

//    ModelAttribute
    @PostMapping("/car")
    public ResponseEntity<?> postCar(@ModelAttribute CarDto carDto) throws IOException {
        boolean success = adminService.postCar(carDto);
        if(success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getallcars")
    public ResponseEntity<?>getAllCars(){
        return ResponseEntity.ok(adminService.getAllCar());
    }

    @DeleteMapping("/delete/car/{id}")
    public ResponseEntity<?>deleteCar(@PathVariable Long id){
        System.out.println("id"+id);
        return ResponseEntity.ok(adminService.deleteCar(id));
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto>getCarDetails(@PathVariable Long id){
        return ResponseEntity.ok(adminService.getCarDetails(id));
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<?>updateCarDetails(@PathVariable Long id, @ModelAttribute CarDto carDetails) throws IOException {
        return ResponseEntity.ok(adminService.updateCar(id, carDetails));
    }

    @GetMapping("/booking")
    public ResponseEntity<List<BookACarDto>>getCarBookings(){
        return ResponseEntity.ok(adminService.getAllCarBooking());
    }

    @PostMapping("/update/booking/{booking_id}/{status}")
    public ResponseEntity<?>UpdatebookingStatus(@PathVariable Long booking_id, @PathVariable String status){
        return ResponseEntity.ok(adminService.changeBookingStatus(booking_id, status));
    }

    @PostMapping("search/car")
    public ResponseEntity<?>searchCar(@RequestBody SearchCarDto searchCarDto){
        return ResponseEntity.ok(adminService.searchCar(searchCarDto));
    }

}
