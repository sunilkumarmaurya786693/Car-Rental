package com.carRental.Car.Rental.project.vdemo.services.auth;

import com.carRental.Car.Rental.project.vdemo.dto.SignUpRequest;
import com.carRental.Car.Rental.project.vdemo.dto.UserDto;
import com.carRental.Car.Rental.project.vdemo.entity.User;
import com.carRental.Car.Rental.project.vdemo.enums.UserRole;
import com.carRental.Car.Rental.project.vdemo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignUpRequest signUpRequest) {
        User user= new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        userDto.setName(createdUser.getName());
        userDto.setEmail(createdUser.getEmail());
        userDto.setUserRole(createdUser.getUserRole());
        return userDto;
    }

    @Override
    public boolean hasCustomerHasEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> adminUser = userRepository.findFirstByUserRole(UserRole.ADMIN);
        if(adminUser.isPresent()){
            return;
        }
        User newAdminUser = new User();
        newAdminUser.setName("admin");
        newAdminUser.setEmail("admin@gmail.com");
        newAdminUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
        newAdminUser.setUserRole(UserRole.ADMIN);
        User createdAdmin = userRepository.save(newAdminUser);
        System.out.println("created admin user"+createdAdmin);
    }
}
