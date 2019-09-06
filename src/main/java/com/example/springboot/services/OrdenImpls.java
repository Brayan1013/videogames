package com.example.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.Dao.IOrdenDao;
import com.example.springboot.entities.Orden;

@Service
public class OrdenImpls implements IOrden {
	
	@Autowired
	private IOrdenDao ordenDao;

	@Override
	public Orden findOrdeById(Long id) {
		return ordenDao.findById(id).orElse(null);
	}

	@Override
	public void deleteOrdenById(Long id) {
		ordenDao.deleteById(id);
	}

	@Override
	public Orden saveOrden(Orden orden) {
		return ordenDao.save(orden);
	}

}
