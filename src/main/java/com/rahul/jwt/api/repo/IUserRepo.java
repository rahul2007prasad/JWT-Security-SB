package com.rahul.jwt.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.jwt.api.entity.User;
import java.util.List;
import java.util.Optional;


public interface IUserRepo  extends JpaRepository<User, Integer>{

	
	//List<User> findByEmail(String email);
	
	Optional<User> findByEmail(String email);
}
