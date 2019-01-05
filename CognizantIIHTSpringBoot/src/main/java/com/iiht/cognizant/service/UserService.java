package com.iiht.cognizant.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.iiht.cognizant.model.Book;
import com.iiht.cognizant.model.User;
import com.iiht.cognizant.model.UserRole;
import com.iiht.cognizant.repository.BookRepository;
import com.iiht.cognizant.repository.UserRepository;
import com.iiht.cognizant.repository.UserRoleRepository;

@Component
public class UserService{
	@Autowired
	UserRepository userDao;
	@Autowired
	UserRoleRepository userroleDao;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("%%%%%%%%%%%");
        userDao.save(user);
        UserRole userRole = new UserRole();
        userRole = (user.getUserRole().iterator().next());
       
        System.out.println(userRole.toString());
        userroleDao.saveAll(user.getUserRole());

	}
}
