package com.example.springboot.Dao;





import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.springboot.entities.Videojuego;

public interface IVideojuegoDao extends PagingAndSortingRepository<Videojuego, Long> {
	
	

}
