package com.example.springboot.Dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.springboot.entities.User;

public interface IUserDao extends CrudRepository<User, Long>{
	
	User findByUsername(String username);
	
	@Query(value="SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
	
	
}
