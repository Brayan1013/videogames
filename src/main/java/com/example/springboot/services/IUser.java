package com.example.springboot.services;

import java.util.List;


import com.example.springboot.entities.User;

public interface IUser {
	
	User findByUsername(String username);
	
	List<User> findAll();
	
	User save(User user);
	
	User findByEmail(String email);
	
	User findById(Long id);
	
	
	
}
