package com.carRental.Car.Rental.project.vdemo.repository;

import com.carRental.Car.Rental.project.vdemo.entity.BookACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAcarRepository extends JpaRepository<BookACar,Long> {
    List<BookACar> findAllByUserId(Long UserId);
}
