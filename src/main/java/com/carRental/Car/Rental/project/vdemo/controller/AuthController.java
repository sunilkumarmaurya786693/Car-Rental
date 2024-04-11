package com.carRental.Car.Rental.project.vdemo.controller;

import com.carRental.Car.Rental.project.vdemo.dto.AuthenticationRequest;
import com.carRental.Car.Rental.project.vdemo.dto.AuthenticationResponse;
import com.carRental.Car.Rental.project.vdemo.dto.SignUpRequest;
import com.carRental.Car.Rental.project.vdemo.dto.UserDto;
import com.carRental.Car.Rental.project.vdemo.entity.User;
import com.carRental.Car.Rental.project.vdemo.repository.UserRepository;
import com.carRental.Car.Rental.project.vdemo.services.auth.AuthService;
import com.carRental.Car.Rental.project.vdemo.services.jwt.UserService;
import com.carRental.Car.Rental.project.vdemo.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?>signupCustomer(@RequestBody SignUpRequest signUpRequest){
        String email = signUpRequest.getEmail();
        if(authService.hasCustomerHasEmail(email)){
            return new ResponseEntity<>("customer is already exist with this email", HttpStatus.CONFLICT);
        }
        UserDto userDto = authService.createCustomer(signUpRequest);

        if(userDto == null) return new ResponseEntity<>("customer not created", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAutheticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }catch (Exception e){
            throw new BadCredentialsException("incorrect user name or password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserid(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }
        return authenticationResponse;

    }
}
