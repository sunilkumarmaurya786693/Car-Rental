package com.carRental.Car.Rental.project.vdemo.entity;

import com.carRental.Car.Rental.project.vdemo.dto.BookACarDto;
import com.carRental.Car.Rental.project.vdemo.enums.BookCarStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class BookACar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long price;

    private BookCarStatus bookCarStatus;

    private Long days;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;

    public BookACarDto getBookingCarDto(){
        BookACarDto bookACarDto = new BookACarDto();
        bookACarDto.setBookCarStatus(getBookCarStatus());
        bookACarDto.setCarId(car.getId());
        bookACarDto.setPrice(getPrice());
        bookACarDto.setDays(getDays());
        bookACarDto.setFromDate(getFromDate());
        bookACarDto.setToDate(getToDate());
        bookACarDto.setId(id);
        bookACarDto.setUserId(user.getId());
        return bookACarDto;
    }

}
