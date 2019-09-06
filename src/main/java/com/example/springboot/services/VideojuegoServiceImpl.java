package com.example.springboot.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.Dao.IVideojuegoDao;
import com.example.springboot.entities.Videojuego;

@Service
public class VideojuegoServiceImpl implements IVideojuego{
	
	@Autowired
	private IVideojuegoDao videojuegoDao;

	public List<Videojuego> findAll() {
		return (List<Videojuego>) videojuegoDao.findAll();
	}

	public Videojuego findById(Long id) {
		return videojuegoDao.findById(id).orElse(null);
	}

	public void deleteById(Long id) {
		videojuegoDao.deleteById(id);
		
	}

	public Videojuego save(Videojuego videojuego) {
		// TODO Auto-generated method stub
		return videojuegoDao.save(videojuego);
	}

	public void deleteByEntity(Videojuego videojuego) {
		videojuegoDao.delete(videojuego);
		
	}

	public Page<Videojuego> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return videojuegoDao.findAll(pageable);
	}

	

	

	
	
	
	

}
