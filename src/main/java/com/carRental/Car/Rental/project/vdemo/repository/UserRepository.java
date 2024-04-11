package com.carRental.Car.Rental.project.vdemo.repository;

import com.carRental.Car.Rental.project.vdemo.entity.User;
import com.carRental.Car.Rental.project.vdemo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstByUserRole(UserRole userRole);
}
