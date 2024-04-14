package com.carRental.Car.Rental.project.vdemo.services.customer;

import com.carRental.Car.Rental.project.vdemo.dto.BookACarDto;
import com.carRental.Car.Rental.project.vdemo.dto.CarDto;
import com.carRental.Car.Rental.project.vdemo.entity.BookACar;
import com.carRental.Car.Rental.project.vdemo.entity.Car;
import com.carRental.Car.Rental.project.vdemo.entity.User;
import com.carRental.Car.Rental.project.vdemo.enums.BookCarStatus;
import com.carRental.Car.Rental.project.vdemo.repository.BookAcarRepository;
import com.carRental.Car.Rental.project.vdemo.repository.CarRepository;
import com.carRental.Car.Rental.project.vdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookAcarRepository bookAcarRepository;
    @Override
    public List<CarDto> getAllCar() {
        List<Car> allCar= carRepository.findAll();
        return allCar.stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookAcar(BookACarDto bookACarDto) {
        Optional<Car> carDetials= carRepository.findById(bookACarDto.getCarId());
        Optional<User> userDetails = userRepository.findById(bookACarDto.getUserId());
        if(carDetials.isPresent() && userDetails.isPresent()){
            BookACar bookACar = new BookACar();
            bookACar.setUser(userDetails.get());
            bookACar.setCar(carDetials.get());
            bookACar.setBookCarStatus(BookCarStatus.PENDING);
            long diffMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
            long days = diffMilliSeconds/(1000*60*60*24);
            bookACar.setDays(days);
            bookACar.setPrice(carDetials.get().getPrice()*days);
            bookACar.setFromDate(bookACarDto.getFromDate());
            bookACar.setToDate(bookACarDto.getToDate());

            bookAcarRepository.save(bookACar);
            return true;
        }
        return false;
    }

    @Override
    public CarDto getCarDetails(Long id) {
        try{
            Optional<Car> carDetails = carRepository.findById(id);
            if(carDetails.isPresent()){
                return carDetails.get().getCarDto();
            }
            throw new Exception("Car does not exist");
        } catch (Exception e){
            System.out.println("error"+e);
        }
        return null;
    }

    @Override
    public List<BookACarDto> getBooking(Long user_id) {
        List<BookACar> bookingDetails = bookAcarRepository.findAllByUserId(user_id);
        List<BookACarDto>allBooking = bookAcarRepository.findAllByUserId(user_id).stream().map(BookACar::getBookingCarDto).collect(Collectors.toList());
        return allBooking;
    }
}
