package com.iiht.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iiht.cognizant.model.User;
import com.iiht.cognizant.model.UserRole;


public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	
	

}
