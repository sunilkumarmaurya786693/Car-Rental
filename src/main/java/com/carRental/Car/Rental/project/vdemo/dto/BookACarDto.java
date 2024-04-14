package com.carRental.Car.Rental.project.vdemo.dto;

import com.carRental.Car.Rental.project.vdemo.enums.BookCarStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookACarDto {
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long price;

    private BookCarStatus bookCarStatus;

    private Long userId;

    private Long carId;

    private Long days;
}
