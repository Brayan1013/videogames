package com.example.springboot.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.entities.Role;

public interface IRoleDao extends CrudRepository<Role, Long> {

}
