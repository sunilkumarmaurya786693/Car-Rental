package com.carRental.Car.Rental.project.vdemo.services.admin;

import com.carRental.Car.Rental.project.vdemo.dto.CarDto;
import com.carRental.Car.Rental.project.vdemo.entity.Car;
import com.carRental.Car.Rental.project.vdemo.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final CarRepository carRepository;

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
}
