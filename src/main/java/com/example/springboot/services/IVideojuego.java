package com.example.springboot.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.springboot.entities.Videojuego;

public interface IVideojuego {
	
	Page<Videojuego> findAll(Pageable pageable);

	Videojuego save(Videojuego videojuego);
	
	List<Videojuego> findAll();
	
	Videojuego findById(Long id);
	
	void deleteById(Long id);
	
	void deleteByEntity(Videojuego videojuego);

}
