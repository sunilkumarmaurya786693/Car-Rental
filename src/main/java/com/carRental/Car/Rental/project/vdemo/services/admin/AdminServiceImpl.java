package com.carRental.Car.Rental.project.vdemo.services.admin;

import com.carRental.Car.Rental.project.vdemo.dto.BookACarDto;
import com.carRental.Car.Rental.project.vdemo.dto.CarDto;
import com.carRental.Car.Rental.project.vdemo.dto.CarDtoListDto;
import com.carRental.Car.Rental.project.vdemo.dto.SearchCarDto;
import com.carRental.Car.Rental.project.vdemo.entity.BookACar;
import com.carRental.Car.Rental.project.vdemo.entity.Car;
import com.carRental.Car.Rental.project.vdemo.enums.BookCarStatus;
import com.carRental.Car.Rental.project.vdemo.repository.BookAcarRepository;
import com.carRental.Car.Rental.project.vdemo.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final CarRepository carRepository;
    private final BookAcarRepository bookAcarRepository;

    @Override
    public boolean postCar(CarDto carDto) throws IOException {
        try{
            Car car = new Car();
            car.setName(carDto.getName());
            car.setBrand(carDto.getBrand());
            car.setDescription(carDto.getDescription());
            car.setColor(carDto.getColor());
            car.setImage(carDto.getImage().getBytes());
            car.setPrice(carDto.getPrice());
            car.setTransmission(carDto.getTransmission());
            car.setType(carDto.getType());
            car.setYear(carDto.getYear());

            Car newCar = carRepository.save(car);
            return true;
        }catch (Exception e){
            System.out.println("error"+e);
            return false;
        }

    }

    @Override
    public List<CarDto> getAllCar() {
        List<Car> allCar= carRepository.findAll();
        return allCar.stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteCar(Long id) {
        try{
            carRepository.deleteById(id);
            return true;
        } catch (Exception e){
            System.out.println("car is not found");
            return false;
        }
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
    public boolean updateCar(Long id, CarDto carDetails) throws IOException {
        Optional<Car> previousCar  = carRepository.findById(id);
        if(previousCar.isPresent()){
            Car newCar = previousCar.get();
            newCar.setYear(carDetails.getYear());
            newCar.setPrice(carDetails.getPrice());
            newCar.setType(carDetails.getType());
            newCar.setTransmission(carDetails.getTransmission());
            newCar.setImage(carDetails.getImage().getBytes());
            newCar.setColor(carDetails.getColor());
            newCar.setDescription(carDetails.getDescription());
            newCar.setBrand(carDetails.getBrand());
            newCar.setName(carDetails.getName());
            carRepository.save(newCar);
            return true;
        }
        return false;
    }

    @Override
    public List<BookACarDto> getAllCarBooking() {
        List<BookACar>bookingCars = bookAcarRepository.findAll();
        return bookingCars.stream().map(BookACar::getBookingCarDto).collect(Collectors.toList());
    }

    @Override
    public Boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookACar> bookingDetails = bookAcarRepository.findById(bookingId);
        if(bookingDetails.isPresent()){
            BookACar existingBooking = bookingDetails.get();
            if(status.equals("approved")){
                existingBooking.setBookCarStatus(BookCarStatus.APPROVED);
            } else{
                existingBooking.setBookCarStatus(BookCarStatus.REJECTED);
            }
            bookAcarRepository.save(existingBooking);
            return true;
        }
        return false;

    }

    @Override
    public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
        Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission(searchCarDto.getTransmission());
        car.setColor(searchCarDto.getColor());

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("branch", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Car>carExample = Example.of(car, exampleMatcher);

        List<Car>carList = carRepository.findAll(carExample);
        CarDtoListDto carDtoListDto = new CarDtoListDto();
        carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));

        return carDtoListDto;
    }
}
