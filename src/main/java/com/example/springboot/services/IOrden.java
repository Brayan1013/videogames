package com.example.springboot.services;

import com.example.springboot.entities.Orden;

public interface IOrden {
	
	Orden findOrdeById(Long id);
	
	void deleteOrdenById(Long id);
	
	Orden saveOrden(Orden orden);

}

