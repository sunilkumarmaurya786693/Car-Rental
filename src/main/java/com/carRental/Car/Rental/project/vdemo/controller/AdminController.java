package com.carRental.Car.Rental.project.vdemo.controller;

import com.carRental.Car.Rental.project.vdemo.dto.CarDto;
import com.carRental.Car.Rental.project.vdemo.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

}
