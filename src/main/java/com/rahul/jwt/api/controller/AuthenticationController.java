package com.rahul.jwt.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.jwt.api.dto.LoginResponse;
import com.rahul.jwt.api.dto.LoginUserDto;
import com.rahul.jwt.api.dto.RegisterUserDto;
import com.rahul.jwt.api.entity.User;
import com.rahul.jwt.api.service.AuthenticationService;
import com.rahul.jwt.api.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final JwtService jwtService;
	
	private final AuthenticationService authenticationService;

	public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
		super();
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto){
		User registerUser = authenticationService.signup(registerUserDto);
		return new ResponseEntity<>(registerUser , HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
		System.out.println(loginUserDto.getEmail() + " " + loginUserDto.getPassword());
		User authenticateduser = authenticationService.authenticate(loginUserDto);
		
		
		String jwtToken = jwtService.generateToken(authenticateduser);
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(
					jwtService.getExpirationTime()	
						);
				
			return new ResponseEntity<>(loginResponse , HttpStatus.OK);	
	}
	
}
