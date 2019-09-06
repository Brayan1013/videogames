package com.example.springboot.services;

import java.util.List;

import com.example.springboot.entities.Role;

public interface IRole {
	
	List<Role> findAll();

}
