package com.rahul.jwt.api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rahul.jwt.api.dto.LoginUserDto;
import com.rahul.jwt.api.dto.RegisterUserDto;
import com.rahul.jwt.api.entity.User;
import com.rahul.jwt.api.repo.IUserRepo;

@Service
public class AuthenticationService {
	
	private final IUserRepo userRepo;
	
	private final PasswordEncoder passEncoder;
	
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(IUserRepo userRepo, PasswordEncoder passEncoder,
			AuthenticationManager authenticationManager) {
		super();
		this.userRepo = userRepo;
		this.passEncoder = passEncoder;
		this.authenticationManager = authenticationManager;
	}
	
	
	public User signup(RegisterUserDto userDto) {
		User user = new User();
		user.setFullName(userDto.getFullName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return userRepo.save(user);
	}
	
	public User authenticate(LoginUserDto userDto) {
		/*
		 * authenticationManager.authenticate( new
		 * UsernamePasswordAuthenticationToken(userDto.getEmail(),
		 * userDto.getPassword())
		 * 
		 * );
		 */
		System.out.println("auth service "+userDto.getEmail() + " " + userDto.getPassword());
		System.out.println("From repo "+ userRepo.findByEmail(userDto.getEmail()));
		return userRepo.findByEmail(userDto.getEmail()).orElseThrow();
		
	}
	
	
}
