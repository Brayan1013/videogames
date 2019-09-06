package com.example.springboot.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.entities.Orden;

public interface IOrdenDao extends CrudRepository<Orden, Long> {

}
