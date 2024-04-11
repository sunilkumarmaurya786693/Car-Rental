package com.carRental.Car.Rental.project.vdemo.repository;

import com.carRental.Car.Rental.project.vdemo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
