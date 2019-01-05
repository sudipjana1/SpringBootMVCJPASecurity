package com.iiht.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iiht.cognizant.model.User;


public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByUsername(String username);
	

}
