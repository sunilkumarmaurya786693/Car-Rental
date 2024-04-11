package com.carRental.Car.Rental.project.vdemo.entity;

import com.carRental.Car.Rental.project.vdemo.dto.CarDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String color;

    private String name;

    private String type;

    private String transmission;

    private String description;

    private Long price;

    private Date year;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    public CarDto getCarDto(){
        CarDto carDto = new CarDto();
        carDto.setId(id);
        carDto.setReturnImage(image);
        carDto.setType(type);
        carDto.setBrand(brand);
        carDto.setColor(color);
        carDto.setDescription(description);
        carDto.setPrice(price);
        carDto.setYear(year);
        carDto.setTransmission(transmission);

        return carDto;
    }

}
